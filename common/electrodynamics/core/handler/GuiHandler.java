package electrodynamics.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.Electrodynamics;
import electrodynamics.client.gui.GuiTray;
import electrodynamics.inventory.container.ContainerTray;
import electrodynamics.item.ItemTray;

public class GuiHandler implements IGuiHandler {

	public enum GuiType {
		TRAY;
	}
	
	public static void openGui(EntityPlayer player, World world, int x, int y, int z, GuiType type) {
		player.openGui(Electrodynamics.instance, type.ordinal(), world, x, y, z);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getGuiElement(ID, player, world, x, y, z, Side.SERVER);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return getGuiElement(ID, player, world, x, y, z, Side.CLIENT);
	}
	
	public Object getGuiElement(int id, EntityPlayer player, World world, int x, int y, int z, Side side) {
		GuiType type = GuiType.values()[id];
		
		switch(type) {
			case TRAY: {
				ItemStack tray = player.inventory.getCurrentItem();
				return side == Side.SERVER ? new ContainerTray(player, ((ItemTray)tray.getItem()).getInventory(tray)) : new GuiTray(player, new ContainerTray(player, ((ItemTray)tray.getItem()).getInventory(tray)));
			}
		}
		
		return null;
	}
	
}
