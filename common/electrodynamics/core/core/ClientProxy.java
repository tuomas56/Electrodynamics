package electrodynamics.core.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import electrodynamics.core.client.render.RenderHoloProjector;
import electrodynamics.core.control.KeyBindingHelper;
import electrodynamics.core.control.KeybindingHandler;
import electrodynamics.core.tileentity.TileEntityHoloPad;

public class ClientProxy extends CommonProxy {

	public void registerTileEntities() {
		super.registerTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloPad.class, new RenderHoloProjector());
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
