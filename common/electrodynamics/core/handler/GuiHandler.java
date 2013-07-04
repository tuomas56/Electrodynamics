package electrodynamics.core.handler;

import electrodynamics.client.gui.GuiTrayKiln;
import electrodynamics.inventory.container.ContainerTrayKiln;
import net.minecraft.client.resources.ResourceLocation;
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
import electrodynamics.lib.client.Textures;
import electrodynamics.lib.core.ModInfo;

public class GuiHandler implements IGuiHandler {

	public enum GuiType {
		METAL_TRAY(Textures.GUI_METAL_TRAY),
		TESLA_MODULE(Textures.GUI_TESLA_MODULE),
		KILN_TRAY(Textures.GUI_KILN_TRAY);
		
		public Textures texture;
		
		private GuiType(Textures texture) {
			this.texture = texture;
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
			case METAL_TRAY: {
				return side == Side.SERVER ? new ContainerTray(player, ((IInventoryItem)held.getItem()).getInventory(held)) : new GuiTray(player, new ContainerTray(player, ((IInventoryItem)held.getItem()).getInventory(held)));
			}
			case TESLA_MODULE:  {
				return side == Side.SERVER ? new ContainerTeslaModule(player, ((IInventoryItem)held.getItem()).getInventory(held)) : new GuiTeslaModule(player, new ContainerTeslaModule(player, ((IInventoryItem)held.getItem()).getInventory(held)));
			}
			case KILN_TRAY:
				return side == Side.SERVER ? new ContainerTrayKiln(player, ((IInventoryItem)held.getItem()).getInventory(held)) : new GuiTrayKiln(player, new ContainerTrayKiln(player, ((IInventoryItem)held.getItem()).getInventory(held)));
		}
		
		return null;
	}
	
}
