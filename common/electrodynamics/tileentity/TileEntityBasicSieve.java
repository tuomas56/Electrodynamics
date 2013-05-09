package electrodynamics.tileentity;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;
import electrodynamics.recipe.RecipeBasicSieve;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.util.InventoryUtil;

public class TileEntityBasicSieve extends TileEntityMachine {

	/** ItemStack currently being read/processed */
	public ItemStack currentlyProcessing;
	
	/** ItemStack processed at last update. Used for checking for changes */
	public ItemStack previouslyProcessing;
	
	/** Progression through the current processing event. <br />
	 * Process is assumed finished when this is equal to zero and the total processing time is greater than zero*/
	public int currentProcessingTime;
	
	/** Total time required for processing "currentlyProcessing" */
	public int totalProcessingTime;
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		if (currentlyProcessing != null) {
			NBTTagCompound nbtCurrentlyProcessing = new NBTTagCompound();
			nbtCurrentlyProcessing.setShort("id", (short) currentlyProcessing.itemID);
			nbtCurrentlyProcessing.setByte("Count", (byte) currentlyProcessing.stackSize);
			nbtCurrentlyProcessing.setShort("Damage", (short) currentlyProcessing.getItemDamage());
			if (currentlyProcessing.hasTagCompound()) {
				nbtCurrentlyProcessing.setTag("tags", currentlyProcessing.stackTagCompound);
			}
			tag.setTag("currProcess", nbtCurrentlyProcessing);
		}
		
		tag.setInteger("currProcessTime", currentProcessingTime);
		tag.setInteger("totalProcessTime", totalProcessingTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		if (tag.hasKey("currProcess")) {
			NBTTagCompound currProcess = tag.getCompoundTag("currProcess");
			currentlyProcessing = ItemStack.loadItemStackFromNBT(currProcess);
		}
		
		currentProcessingTime = tag.getInteger("currProcessTime");
		totalProcessingTime = tag.getInteger("totalProcessTime");
	}
	
	public void setItem(ItemStack stack) {
		previouslyProcessing = currentlyProcessing;
		
		if (stack != null) {
			currentlyProcessing = stack.copy();
		} else {
			currentlyProcessing = null;
		}
	}
	
	public void setProcessTime(int time) {
		totalProcessingTime = time;
		currentProcessingTime = time;
	}
	
	public TileEntityBasicSieve() {
		currentlyProcessing = null;
		previouslyProcessing = null;
		currentProcessingTime = 0;
		totalProcessingTime = 0;
	}
	
	@Override
	public void updateEntity() {
		if (this.worldObj.isRemote) return;

		if (canProcess()) {
			if (totalProcessingTime > 0) {
				if (currentProcessingTime > 0) {
					currentProcessingTime--;
				} else {
					process();
				}
			}
		} else {
			if (this.worldObj.getWorldTime() % 5 == 0) {
				scanForItems();
			}
		}
	} 
	
	@SuppressWarnings("unchecked")
	private void scanForItems() {
		AxisAlignedBB scanArea = AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
		List<EntityItem> detectedItems = this.worldObj.getEntitiesWithinAABB(EntityItem.class, scanArea);
	
		if (detectedItems != null && detectedItems.size() > 0) {
			EntityItem detected = detectedItems.get(0);
			ItemStack detectedIS = detected.getEntityItem();
			RecipeBasicSieve recipe = CraftingManager.getInstance().sieveManager.getRecipe(detectedIS);
			
			if (recipe != null) {
				ItemStack item = detectedIS.copy();
				item.stackSize = 1;
				
				setItem(item);
				setProcessTime(recipe.processingTime);
				
				if (detectedIS.stackSize > 1) {
					detectedIS.stackSize--;
				} else {
					detected.setDead();
				}
			}
		}
	}
	
	private void process() {
		RecipeBasicSieve recipe = CraftingManager.getInstance().sieveManager.getRecipe(currentlyProcessing);
		Random rand = new Random();
		
		for (WeightedRecipeOutput out : recipe.itemOutputs) {
			if (out.willOutput(rand)) {
				InventoryUtil.dispenseOutSide(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.DOWN, out.output.copy(), new Random());
			}
		}
		
		setItem(null);
		currentProcessingTime = 0;
		totalProcessingTime = 0;
	}
	
	private boolean canProcess() {
		if (currentlyProcessing == null) return false;
		if (CraftingManager.getInstance().sieveManager.getRecipe(currentlyProcessing) == null) return false;
		
		return true;
	}

}
