package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import electrodynamics.tileentity.machine.TileEntityMachine;

public abstract class TileEntityInventoryWrapper extends TileEntityMachine implements IInventory, ISidedInventory {

	private IInventory inventory;
	
	public TileEntityInventoryWrapper(IInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public int getSizeInventory() {
		return this.inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventory.getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return this.inventory.decrStackSize(slot, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return this.inventory.getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventory.setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInvName() {
		return this.inventory.getInvName();
	}

	@Override
	public boolean isInvNameLocalized() {
		return this.inventory.isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit() {
		return this.inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openChest() {
		this.inventory.openChest();
	}

	@Override
	public void closeChest() {
		this.inventory.closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return this.inventory.isItemValidForSlot(slot, stack);
	}
	
	@Override
    public boolean canInsertItem(int slot, ItemStack stack, int amount) {
		return true;
	}

	@Override
    public boolean canExtractItem(int slot, ItemStack stack, int amount) {
		return true;
	}
	
	@Override
	public abstract int[] getAccessibleSlotsFromSide(int side);
	
}
