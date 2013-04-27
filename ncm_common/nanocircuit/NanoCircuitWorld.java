package nanocircuit;

import nanocircuit.core.configuration.ConfigurationSettings;
import nanocircuit.core.lib.BlockIDs;
import nanocircuit.core.lib.ModInfo;
import nanocircuit.world.block.BlockHandler;
import nanocircuit.world.core.CommonProxy;
import nanocircuit.world.core.WorldGenOre;
import nanocircuit.world.lib.Ore;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "NCWorld", name = "NanoCircuit World", version = ModInfo.VERSION, dependencies = "required-after:NCCore")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class NanoCircuitWorld {
	@Instance("NCWorld")
	public static NanoCircuitWorld instance;
	@SidedProxy(clientSide = "nanocircuit.world.core.ClientProxy", serverSide = "nanocircuit.world.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		BlockHandler.initializeBlocks();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		if (ConfigurationSettings.MAGNETITE_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.NICKEL.ordinal(), ConfigurationSettings.NICKEL_SPAWN_AMOUNT, ConfigurationSettings.NICKEL_MAX_Y_LEVEL));
		}
		
		if (ConfigurationSettings.NICKEL_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.MAGNETITE.ordinal(), ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL));
		}
	}

}
