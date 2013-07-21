package electrodynamics.tileentity.structure;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.util.InventoryUtil;

public class TileEntityHatch extends TileEntityStructure implements IInventory, ISidedInventory {

	@Override
	public void updateEntity() {
		if (this.isValidStructure()) {
			if (worldObj.getWorldTime() % 5 == 0) { // Runs four times per second
				if (getCentralInventory() != null && getSizeInventory() > 0) {
					for (int i=0; i<getSizeInventory(); i++) {
						ItemStack stack = getStackInSlot(i);

						if (stack != null) {
							setInventorySlotContents(i, null);
							int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
							InventoryUtil.dispenseOutSide(worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.getOrientation(meta), stack.copy(), new Random());
						}
					}
				}
			}
		}
	}
	
	@Override
	public int getSizeInventory() {
		return getCentralInventory().getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return getCentralInventory().getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return getCentralInventory().decrStackSize(slot, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getCentralInventory().getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		getCentralInventory().setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInvName() {
		return getCentralInventory().getInvName();
	}

	@Override
	public boolean isInvNameLocalized() {
		return getCentralInventory().isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit() {
		return getCentralInventory().getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return getCentralInventory().isUseableByPlayer(entityplayer);
	}

	@Override
	public void openChest() {
		getCentralInventory().openChest();
	}

	@Override
	public void closeChest() {
		getCentralInventory().closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return getCentralInventory().isItemValidForSlot(slot, stack);
	}

	public IInventory getCentralInventory() {
		if (this.isValidStructure()) {
			TileEntityStructure central = this.getCentralTileEntity();
			
			if (central instanceof IInventory) {
				return (IInventory)central;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		int size = getCentralInventory().getSizeInventory();
		int[] slots = new int[size];
		
		for (int i=0; i<size; i++) {
			slots[i] = i;
		}
		
		return slots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int amount) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int amount) {
		return true;
	}

}
