package electrodynamics.api;

import cpw.mods.fml.common.Loader;
import electrodynamics.api.crafting.ICraftingManager;

public class ElectrodynamicsAPI {

	public static ICraftingManager getCraftingManager() {
		return (ICraftingManager) Loader.instance().getIndexedModList().get("ED").getMod();
	}
	
}
