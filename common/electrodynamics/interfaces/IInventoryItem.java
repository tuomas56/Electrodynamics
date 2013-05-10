package electrodynamics.interfaces;

import net.minecraft.item.ItemStack;
import electrodynamics.inventory.InventoryItem;

public interface IInventoryItem {

	public InventoryItem getInventory(ItemStack stack);
	
}
