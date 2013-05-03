package electrodynamics.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.client.fx.FXBeam;
import electrodynamics.client.render.RenderBeam;
import electrodynamics.client.render.RenderHoloProjector;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;
import electrodynamics.tileentity.TileEntityHoloPad;

public class ClientProxy extends CommonProxy {

	public void registerTileEntities() {
		super.registerTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloPad.class, new RenderHoloProjector());
	}
	
	public void registerEntities() {
		super.registerEntities();
		
		RenderingRegistry.registerEntityRenderingHandler(FXBeam.class, new RenderBeam());
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
