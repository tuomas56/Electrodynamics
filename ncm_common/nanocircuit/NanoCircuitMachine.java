package nanocircuit;

import nanocircuit.core.lib.ModInfo;
import nanocircuit.machine.core.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "NC|Machine", name = "NanoCircuit - Machine", version = ModInfo.VERSION, dependencies = "required-after:NC|Core;required-after:NC|World")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitMachine {
	@Instance("NC|Machine")
	public static NanoCircuitMachine instance;
	@SidedProxy(clientSide = "nanocircuit.machine.core.ClientProxy", serverSide = "nanocircuit.machine.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}