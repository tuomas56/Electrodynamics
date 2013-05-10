package electrodynamics.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.handler.GuiHandler;
import electrodynamics.interfaces.IInventoryItem;
import electrodynamics.inventory.InventoryItem;

public class ItemTray extends Item implements IInventoryItem {

	public ItemTray(int id) {
		super(id);
		setMaxStackSize(64);
		setMaxDamage(0);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!world.isRemote && player.isSneaking()) {
			GuiHandler.openGui(player, world, (int)player.posX, (int)player.posY, (int)player.posZ, GuiHandler.GuiType.TRAY);
		}
		
		return stack;
	}

	@Override
	public InventoryItem getInventory(ItemStack stack) {
		if (stack.getItem() instanceof IInventoryItem) {
			return new InventoryItem(9, stack);
		}
		
		return null;
	}
	
}
