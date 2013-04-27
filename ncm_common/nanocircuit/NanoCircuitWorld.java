package nanocircuit;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import nanocircuit.core.lib.ModInfo;
import nanocircuit.world.core.CommonProxy;

@Mod(modid = "NC:World", name = "NanoCircuit World", version = ModInfo.VERSION, dependencies = "required-after:NCCore")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitWorld {
	@Instance("NC:World")
	public static NanoCircuitWorld instance;
	@SidedProxy(clientSide = "nanocircuit.world.ClientProxy", serverSide = "nanocircuit.world.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}
