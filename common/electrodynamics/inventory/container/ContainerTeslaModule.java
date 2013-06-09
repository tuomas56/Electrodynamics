package electrodynamics.inventory.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import electrodynamics.api.tool.IElMagModule;
import electrodynamics.inventory.InventoryItem;
import electrodynamics.util.InventoryUtil;

public class ContainerTeslaModule extends Container {

	public InventoryItem inventory;
	
	public EntityPlayer activePlayer;
	
	public int activeSlot;
	
	public int armorType;
	
	public ContainerTeslaModule(EntityPlayer player, InventoryItem inventory) {
		this.inventory = inventory;
		inventory.parentContainer = this;
		this.activePlayer = player;
		this.activeSlot = InventoryUtil.getActiveSlot(player.inventory.currentItem, inventory);
		this.armorType = ((ItemArmor)this.inventory.parent.getItem()).armorType;
		
		// Tray Inventory
		this.addSlotToContainer(new SlotModule(inventory, 0, 8, 8, armorType));

		// Player Inventory
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, i * 18 + 51));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 58 + 51));
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

			if (par2 < this.inventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
	
	public class SlotModule extends Slot {

		private int armorType;
		
		public SlotModule(IInventory par1iInventory, int par2, int par3, int par4, int armorType) {
			super(par1iInventory, par2, par3, par4);
			this.armorType = armorType;
		}
		
		@Override
		public boolean isItemValid(ItemStack stack) {
			if (stack.getItem() instanceof IElMagModule) {
				for (int type : ((IElMagModule)stack.getItem()).validArmorTypes(stack)) {
					if (type == this.armorType) {
						return true;
					}
				}
			}
			
			return false;
		}
		
	}
	
}
