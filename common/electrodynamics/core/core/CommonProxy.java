package electrodynamics.core.core;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.core.lib.Strings;
import electrodynamics.core.tileentity.TileEntityHoloPad;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityHoloPad.class, Strings.BLOCK_HOLO_PAD_NAME);
	}
	
}
