package electrodynamics.api.crafting;

import cpw.mods.fml.common.Loader;

public class ElectrodynamicsAPI {

	public static ICraftingManager getCraftingManager() {
		return (ICraftingManager) Loader.instance().getIndexedModList().get("ED").getMod();
	}
	
}
