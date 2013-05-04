package electrodynamics.core;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.client.fx.FXBeam;
import electrodynamics.client.render.entity.RenderBeam;
import electrodynamics.client.render.item.RenderItemTable;
import electrodynamics.client.render.tileentity.RenderTable;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.lib.BlockIDs;
import electrodynamics.tileentity.TileEntityTable;

public class ClientProxy extends CommonProxy {

	public void registerTileEntities() {
		super.registerTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());
	}
	
	public void registerEntities() {
		super.registerEntities();
		
		RenderingRegistry.registerEntityRenderingHandler(FXBeam.class, new RenderBeam());
	}
	
	@Override
	public void registerRenders() {
		MinecraftForgeClient.registerItemRenderer(BlockIDs.BLOCK_TABLE_ID, new RenderItemTable());
	}
	
	@Override
	public void setKeyBinding(String name, int value, boolean repeats) {
		KeyBindingHelper.addKeyBinding(name, value, repeats);
	}
	
	@Override
	public void registerKeyBindings() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
	}
	
}
