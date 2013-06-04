package electrodynamics.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.Electrodynamics;
import electrodynamics.client.gui.GuiTeslaModule;
import electrodynamics.client.gui.GuiTray;
import electrodynamics.interfaces.IInventoryItem;
import electrodynamics.inventory.container.ContainerTeslaModule;
import electrodynamics.inventory.container.ContainerTray;
import electrodynamics.lib.core.ModInfo;

public class GuiHandler implements IGuiHandler {

	public enum GuiType {
		TRAY("/gui/trap.png"),
		TESLA_MODULE(ModInfo.RESOURCE_DIR + "/textures/gui/teslaModule.png"),
		TRAY_KILN("/gui/trayKiln.png"); // todo the tray gui image
		
		public String guiFile;
		
		private GuiType(String guiFile) {
			this.guiFile = guiFile;
		}
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
		ItemStack held = player.inventory.getCurrentItem();
		
		switch(type) {
			case TRAY: {
				return side == Side.SERVER ? new ContainerTray(player, ((IInventoryItem)held.getItem()).getInventory(held)) : new GuiTray(player, new ContainerTray(player, ((IInventoryItem)held.getItem()).getInventory(held)));
			}
			case TESLA_MODULE:  {
				return side == Side.SERVER ? new ContainerTeslaModule(player, ((IInventoryItem)held.getItem()).getInventory(held)) : new GuiTeslaModule(player, new ContainerTeslaModule(player, ((IInventoryItem)held.getItem()).getInventory(held)));
			}
			case TRAY_KILN:
				return side == Side.SERVER ? null : null; // todo: the tray container and gui container.
		}
		
		return null;
	}
	
}
