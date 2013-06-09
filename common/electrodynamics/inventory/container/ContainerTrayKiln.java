package electrodynamics.inventory.container;

import electrodynamics.inventory.InventoryItem;
import electrodynamics.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrayKiln extends Container {

	public InventoryItem inventory;

	public EntityPlayer activePlayer;

	public int activeSlot;

	public ContainerTrayKiln(EntityPlayer player, InventoryItem inventory) {
		this.inventory = inventory;
		inventory.parentContainer = this;
		this.activePlayer = player;
		this.activeSlot = InventoryUtil.getActiveSlot(player.inventory.currentItem, inventory);
		
		// Tray Inventory
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 2, 44 + j * 18, 26 + i * 18));
			}
		}
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				this.addSlotToContainer(new Slot(inventory, 4 + j + i * 2, 98 + j * 18, 26 + i * 18) {
					@Override
					public boolean isItemValid(ItemStack par1ItemStack){
						return false; // don't let the player place items in these slots, as they are output-only.
					}
				});
			}
		}

		// Player Inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	public void onCraftGuiClosed(EntityPlayer player) {
		if (!player.worldObj.isRemote) {
			this.activePlayer.setCurrentItemOrArmor(0, this.inventory.parent);
			this.activePlayer.inventory.onInventoryChanged();
		}
	}
	
	@Override
	public ItemStack slotClick(int slot, int x, int y, EntityPlayer player) {
		if (slot == this.activeSlot) return null;
		return super.slotClick(slot, x, y, player);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		if (par2 == this.activeSlot) return null;
		
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 < 8) { // tray slots
				if (!this.mergeItemStack(itemstack1, 8, 8 + 36, true)) { // move to player's inventory
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 4, false)) { // move to input slots
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}

	@Override
	protected boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean reverse) {
		boolean returnValue = false;
		int i = slotMin;

		if( reverse ) {
			i = slotMax - 1;
		}

		Slot slot;
		if( itemStack.isStackable() ) {
			while( itemStack.stackSize > 0 && (!reverse && i < slotMax || reverse && i >= slotMin) ) {
				slot = (Slot) this.inventorySlots.get( i );
				ItemStack slotStack = slot.getStack();

				if( slotStack != null && slotStack.itemID == itemStack.itemID && (!itemStack.getHasSubtypes()
						|| itemStack.getItemDamage() == slotStack.getItemDamage()) && ItemStack.areItemStackTagsEqual( itemStack, slotStack ) ) {
					int total = slotStack.stackSize + itemStack.stackSize;
					int max = Math.min( itemStack.getMaxStackSize(), slot.getSlotStackLimit() );

					if( total <= max ) {
						itemStack.stackSize = 0;
						slotStack.stackSize = total;
						slot.onSlotChanged();
						returnValue = true;
					} else if( slotStack.stackSize < max ) {
						itemStack.stackSize -= max - slotStack.stackSize;
						slotStack.stackSize = max;
						slot.onSlotChanged();
						returnValue = true;
					}
				}

				if( reverse ) {
					--i;
				} else {
					++i;
				}
			}
		}

		if( itemStack.stackSize > 0 ) {
			if( reverse ) {
				i = slotMax - 1;
			} else {
				i = slotMin;
			}

			while( !reverse && i < slotMax || reverse && i >= slotMin ) {
				slot = (Slot) this.inventorySlots.get( i );
				ItemStack slotStack = slot.getStack();

				if( slotStack == null ) {
					int max = Math.min( itemStack.getMaxStackSize(), slot.getSlotStackLimit() );
					max = Math.min( itemStack.stackSize, max );
					slot.putStack( itemStack.copy() );
					slot.onSlotChanged();
					itemStack.stackSize -= max;
					return true;
				}

				if( reverse ) {
					--i;
				} else {
					++i;
				}
			}
		}

		return returnValue;
	}

	@Override
	protected void retrySlotClick(int slotID, int mouseButton, boolean shiftDown, EntityPlayer player) {
		if( slotID >= 9 && mouseButton == 1 ) return;
		super.retrySlotClick( slotID, mouseButton, shiftDown, player );
	}
	
}
