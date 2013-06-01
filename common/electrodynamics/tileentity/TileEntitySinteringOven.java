package electrodynamics.tileentity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.CoreUtils;
import electrodynamics.inventory.InventoryItem;
import electrodynamics.item.EDItems;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.RecipeSinteringOven;
import electrodynamics.util.InventoryUtil;
import electrodynamics.util.ItemUtil;

public class TileEntitySinteringOven extends TileEntityMachine {

	public final float ROTATIONAL_MAX = 1.5F;
	
	public float doorAngle = 0;
	
	/** Does the oven currently have a tray in it. Primarily used for logic purposes */
	public boolean hasTray = false;
	
	public boolean open = false;
	
	/** Based off of furnace fuel burn time, but constantly drained "to keep oven hot" */
	public int fuelLevel;
	
	/** Set to the recipes value when tray is input. If this is greater than zero, a valid recipe is present */
	public int totalCookTime;
	
	/** Set to the recipes value when tray is input, when equal to zero, tray contents are replaced with recipe output */
	public int currentCookTime;
	
	/** Tray's internal inventory */
	public InventoryItem trayInventory;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (CoreUtils.isServer(worldObj)) {
			if (fuelLevel > 0) {
				--this.fuelLevel;
				
				if (this.open) {
					for (EntityLiving living : getEntitiesInFireRange()) {
						living.setFire(1);
					}
				} else {
					if (totalCookTime > 0) {
						if (trayInventory != null) {
							if (currentCookTime == 0) {
								RecipeSinteringOven recipe = CraftingManager.getInstance().ovenManager.getRecipe(Arrays.asList(this.trayInventory.inventory));
							
								if (recipe != null) {
									doProcess( recipe );
									
									this.totalCookTime = 0;
									this.currentCookTime = 0;
									
									sendUpdatePacket(Side.CLIENT);
									return;
								}
							} else {
								--currentCookTime;
							}
						} else {
							totalCookTime = 0;
						}
					} else {
						if (trayInventory != null) {
							RecipeSinteringOven recipe = CraftingManager.getInstance().ovenManager.getRecipe(Arrays.asList(this.trayInventory.inventory));
							
							if (recipe != null) {
								this.totalCookTime = this.currentCookTime = recipe.processingTime;
								
								sendUpdatePacket(Side.CLIENT);
								return;
							}
						}
					}
				}
			}
		}
		
		if (CoreUtils.isClient(worldObj)) {
			if (open && doorAngle <= ROTATIONAL_MAX) {
				doorAngle += 0.2F;
			} else if (!open && doorAngle > 0) {
				doorAngle -= 0.2F;
			}
			
			if (doorAngle < 0) {
				doorAngle = 0;
			}
		}
	}
	
	@Override
	public void onBlockBreak() {
		if (this.hasTray) {
			InventoryUtil.ejectItem(worldObj, xCoord, yCoord, zCoord, ForgeDirection.UP, this.trayInventory.parent.copy(), new Random());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityLiving> getEntitiesInFireRange() {
		return this.worldObj.getEntitiesWithinAABB(EntityLiving.class, getFireDetectionBoundingBox());
	}
	
	public AxisAlignedBB getFireDetectionBoundingBox() {
		return AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).addCoord(1 * this.rotation.offsetX, 0, 1 * this.rotation.offsetZ);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("open", open);
		nbt.setBoolean("hasTray", hasTray);
		nbt.setInteger("fuelLevel", fuelLevel);
		if (this.hasTray) {
			this.trayInventory.writeToNBT(nbt);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.open = nbt.getBoolean("open");
		this.hasTray = nbt.getBoolean("hasTray");
		this.fuelLevel = nbt.getInteger("fuelLevel");
		if (nbt.hasKey("Items")) {
			this.trayInventory = new InventoryItem(9, new ItemStack(EDItems.itemTray));
			this.trayInventory.readFromNBT(nbt);
		}
	}
	
	@Override
	public void onBlockActivated(EntityPlayer player) {
		if (player.isSneaking()) {
			this.open = !this.open;
			
			sendUpdatePacket(Side.CLIENT);
			return;
		}
		
		if (this.open) {
			if (player.getCurrentEquippedItem() != null) {
				ItemStack currentItem = player.getCurrentEquippedItem();
				
				if (ItemUtil.getFuelValue(currentItem) > 0) {
					this.fuelLevel += ItemUtil.getFuelValue(currentItem);
					--currentItem.stackSize;
					if( currentItem.itemID == Item.bucketLava.itemID ) { // return the empty bucket when using lava as fuel.
						ItemStack bucket = new ItemStack( Item.bucketEmpty, 1 );
						if( currentItem.stackSize == 0 )
							player.setCurrentItemOrArmor( 0, bucket );
						else
							player.inventory.addItemStackToInventory( bucket );
					}
					
					sendUpdatePacket(Side.CLIENT);
					((EntityPlayerMP)player).updateHeldItem();
				} else if (!hasTray && currentItem.getItem() == EDItems.itemTray) {
					this.hasTray = true;
					this.trayInventory = new InventoryItem(9, currentItem.copy());
					--currentItem.stackSize;
					
					sendUpdatePacket(Side.CLIENT);
					((EntityPlayerMP)player).updateHeldItem();
				}
				return;
			}
			
			if (this.hasTray) {
				player.setCurrentItemOrArmor( 0, trayInventory.parent.copy() );
				
				this.hasTray = false;
				this.trayInventory = null;
				this.currentCookTime = 0;
				this.totalCookTime = 0;
				
				sendUpdatePacket(Side.CLIENT);
				((EntityPlayerMP)player).updateHeldItem();
				return;
			}
		}
	}

	private void doProcess(RecipeSinteringOven recipe) {
		// Consume the inputs.
		trayLoop:
		for( int i = 0; i < trayInventory.getSizeInventory(); i++ ) {
			ItemStack itemStack = trayInventory.getStackInSlot( i );
			if( itemStack == null ) continue;

			for( ItemStack input : recipe.itemInputs ) {
				if( input.isItemEqual( itemStack ) ) {
					trayInventory.setInventorySlotContents( i, null );
					continue trayLoop;
				}
			}
		}

		// Give the outputs
		for( ItemStack output : recipe.itemOutputs ) {
			InventoryUtil.addToInventory( trayInventory, output.copy() );
		}
	}

}
