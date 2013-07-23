package electrodynamics.inventory.container;

import electrodynamics.inventory.InventoryItem;
import electrodynamics.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class ContainerInventory extends Container {

	public InventoryItem inventory;

	public EntityPlayer activePlayer;

	public int activeSlot;
	
	public ContainerInventory(EntityPlayer player, InventoryItem inventory) {
		this.inventory = inventory;
		inventory.parentContainer = this;
		this.activePlayer = player;
		this.activeSlot = InventoryUtil.getActiveSlot(player.inventory.currentItem, inventory);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
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
	
}
