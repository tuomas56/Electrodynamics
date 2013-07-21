package electrodynamics.inventory.wrapper;

import java.util.Stack;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryWrapperStack implements IInventory {

	private Stack<ItemStack> inventoryStack = new Stack<ItemStack>();
	
	public Stack<ItemStack> getStack() {
		return this.inventoryStack;
	}
	
	@Override
	public int getSizeInventory() {
		return this.inventoryStack.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inventoryStack.get(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inventoryStack.set(slot, stack);
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}

	@Override
	public void openChest() {
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
	
}
