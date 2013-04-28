package nanocircuit;

import nanocircuit.core.lib.ModInfo;
import nanocircuit.logic.core.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "NC|Logic", name = "NanoCircuit - Logic", version = ModInfo.VERSION, dependencies = "required-after:NC|Core;required-after:NC|World;required-after:NC|Machine")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitLogic {
	@Instance("NC|Logic")
	public static NanoCircuitLogic instance;
	@SidedProxy(clientSide = "nanocircuit.logic.core.ClientProxy", serverSide = "nanocircuit.logic.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}
