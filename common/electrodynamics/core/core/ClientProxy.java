package electrodynamics.core.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import electrodynamics.core.client.render.RenderHoloProjector;
import electrodynamics.core.tileentity.TileEntityHoloPad;

public class ClientProxy extends CommonProxy {

	public void registerTileEntities() {
		super.registerTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloPad.class, new RenderHoloProjector());
	}
	
}
