package electrodynamics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import electrodynamics.core.lib.ModInfo;
import electrodynamics.logic.core.CommonProxy;

@Mod(modid = "ED|Logic", name = "Electrodynamics - Logic", version = ModInfo.VERSION, dependencies = "required-after:ED|Core;required-after:ED|World;required-after:ED|Machine")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ElectroDynamicsLogic {
	@Instance("ED|Logic")
	public static ElectroDynamicsLogic instance;
	@SidedProxy(clientSide = "electrodynamics.logic.core.ClientProxy", serverSide = "electrodynamics.logic.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}
