package electrodynamics.core;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.EDBlocks;
import electrodynamics.client.render.block.RenderBlockRedWire;
import electrodynamics.client.render.entity.RenderBeam;
import electrodynamics.client.render.item.RenderItemTable;
import electrodynamics.client.render.tileentity.RenderSinteringFurnace;
import electrodynamics.client.render.tileentity.RenderTable;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.entity.EntityBeam;
import electrodynamics.tileentity.TileEntitySinteringFurnace;
import electrodynamics.tileentity.TileEntityTable;

public class ClientProxy extends CommonProxy {

	public void preInitClient() {
		
	}
	
	public void initClient() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySinteringFurnace.class, new RenderSinteringFurnace());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTable.class, new RenderTable());
		
		MinecraftForgeClient.registerItemRenderer(EDBlocks.blockTable.blockID, new RenderItemTable());
		
		RenderingRegistry.registerBlockHandler(new RenderBlockRedWire());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBeam.class, new RenderBeam());
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
