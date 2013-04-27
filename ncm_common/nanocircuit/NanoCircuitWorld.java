package nanocircuit;

import java.io.File;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import nanocircuit.core.lib.ModInfo;
import nanocircuit.world.block.BlockHandler;
import nanocircuit.world.core.CommonProxy;
import nanocircuit.world.core.WorldConfiguration;

@Mod(modid = "NCWorld", name = "NanoCircuit World", version = ModInfo.VERSION, dependencies = "required-after:NCCore")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitWorld {
	@Instance("NCWorld")
	public static NanoCircuitWorld instance;
	@SidedProxy(clientSide = "nanocircuit.world.core.ClientProxy", serverSide = "nanocircuit.world.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		WorldConfiguration.initializeDefaults();
		WorldConfiguration.handleConfig(new File(event.getModConfigurationDirectory(), ModInfo.CORE_CONFIG));
	
		BlockHandler.initializeBlocks();
	}

	@Init
	public void init(FMLInitializationEvent event) {

	}

}
