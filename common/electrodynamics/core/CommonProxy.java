package electrodynamics.core;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.lib.Strings;
import electrodynamics.tileentity.TileEntityHoloPad;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityHoloPad.class, Strings.BLOCK_HOLO_PAD_NAME);
	}
	
	public void setKeyBinding(String name, int value, boolean repeats) {
		
	}
	
	public void registerKeyBindings() {
		
	}
	
}
