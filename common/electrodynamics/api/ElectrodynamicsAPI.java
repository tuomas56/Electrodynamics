package electrodynamics.api;

import cpw.mods.fml.common.Loader;

public class ElectrodynamicsAPI {

	public static IEDApi getAPIInterface() {
		return (IEDApi) Loader.instance().getIndexedModList().get("ED").getMod();
	}
	
}
