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

@Mod(modid = "NC|Logic", name = "NanoCircuit - Logic", version = ModInfo.VERSION, dependencies = "required-after:NC|Core;required-after:NC|World;required-after:NC|Machine")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ElectroDynamicsLogic {
	@Instance("NC|Logic")
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
