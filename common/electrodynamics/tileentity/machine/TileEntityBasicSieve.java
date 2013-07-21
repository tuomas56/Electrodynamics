package electrodynamics.tileentity.machine;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;
import electrodynamics.inventory.SimpleInventory;
import electrodynamics.recipe.RecipeSieve;
import electrodynamics.recipe.manager.CraftingManager;
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

	/** The current (internal) slot being processed. */
	public int processedSlot = 0;

	/** The internal queue for the sieve */
	private SimpleInventory inventory = new SieveInternalInventory();
	
	@Override
	public void onBlockBreak() {
		if (currentlyProcessing != null) {
			InventoryUtil.ejectItem(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP, currentlyProcessing.copy(), new Random());
		}
		
		for (int i=0; i<inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			
			if (stack != null) {
				InventoryUtil.ejectItem(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP, stack.copy(), new Random());
			}
		}
	}
	
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
		tag.setInteger("processedSlot", processedSlot);

		inventory.writeToNBT( tag );
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
		processedSlot = tag.getInteger("processedSlot");

		inventory.readFromNBT(tag);
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

		boolean tick = this.worldObj.getWorldTime() % 5 == 0;
		if( tick )
			scanForItems();

		if (canProcess()) {
			if (totalProcessingTime > 0) {
				if (currentProcessingTime > 0) {
					currentProcessingTime--;
				} else {
					process();
				}
			}
		} else {
			if (tick) {
				ItemStack item = getNextItemToProcess();
				if( item != null ) {
					RecipeSieve recipe = CraftingManager.getInstance().sieveManager.getRecipe(item);
					setItem(item);
					setProcessTime(recipe.processingTime);
				}
			}
		}
	} 
	
	@SuppressWarnings("unchecked")
	private void scanForItems() {
		AxisAlignedBB scanArea = AxisAlignedBB.getBoundingBox(xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
		List<EntityItem> detectedItems = this.worldObj.getEntitiesWithinAABB(EntityItem.class, scanArea);

		if (detectedItems != null && detectedItems.size() > 0) {
			for( EntityItem detected : detectedItems ) {
				ItemStack detectedIS = detected.getEntityItem();
				if( isValidItem( detectedIS ) ) {
					ItemStack remaining = InventoryUtil.addToInventory( inventory, detectedIS );
					if( remaining == null ) {
						detected.setDead();
					} else {
						detectedIS.stackSize = remaining.stackSize;
						break;
					}
				}
			}
		}
	}
	
	private void process() {
		RecipeSieve recipe = CraftingManager.getInstance().sieveManager.getRecipe(currentlyProcessing);
		Random rand = new Random();
		
		for (WeightedRecipeOutput out : recipe.itemOutputs) {
			if (out.willOutput(rand)) {
				InventoryUtil.dispenseOutSide(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.DOWN, out.output.copy(), new Random());
			}
		}
		
		setItem(null);
		currentProcessingTime = 0;
		totalProcessingTime = 0;
		inventory.decrStackSize( processedSlot, 1 );
	}
	
	private boolean canProcess() {
		if (currentlyProcessing == null) return false;
		if (CraftingManager.getInstance().sieveManager.getRecipe(currentlyProcessing) == null) return false;
		
		return true;
	}

	private ItemStack getNextItemToProcess() {
		// make sure it only iterate once through the inventory.
		for( int i = 0; i < inventory.getSizeInventory(); i++ ) {
			ItemStack current = inventory.getStackInSlot( processedSlot );
			if( current != null ) {
				return current;
			}
			processedSlot = (++processedSlot % CAPACITY);
		}
		return null;
	}

	/**
	 * Whether if the item is valid to be processed by the Sieve.
	 *
	 * @param item the item to check.
	 */
	public static boolean isValidItem(ItemStack item) {
		if( item != null ) {
			RecipeSieve recipe = CraftingManager.getInstance().sieveManager.getRecipe(item);
			return recipe != null;
		}
		return false;
	}

	private static final int CAPACITY = 64; // queue capacity.

	private static class SieveInternalInventory extends SimpleInventory {

		public SieveInternalInventory() {
			super( CAPACITY, "inventoryBasicOven" );
		}

		@Override
		public int getInventoryStackLimit() {
			return 1;
		}

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			return isValidItem( itemstack );
		}
	}

}
