package electrodynamics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.core.block.BlockHandler;
import electrodynamics.core.configuration.ConfigurationSettings;
import electrodynamics.core.lib.BlockIDs;
import electrodynamics.core.lib.ModInfo;
import electrodynamics.world.core.CommonProxy;
import electrodynamics.world.core.WorldGenOre;
import electrodynamics.world.lib.Ore;

@Mod(modid = "NC|World", name = "NanoCircuit - World", version = ModInfo.VERSION, dependencies = "required-after:NC|Core")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ElectroDynamicsWorld {
	@Instance("NC|World")
	public static ElectroDynamicsWorld instance;
	@SidedProxy(clientSide = "electrodynamics.world.core.ClientProxy", serverSide = "electrodynamics.world.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		BlockHandler.initializeBlocks();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		if (ConfigurationSettings.MAGNETITE_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.NICKEL.ordinal(), ConfigurationSettings.NICKEL_SPAWN_AMOUNT, ConfigurationSettings.NICKEL_MAX_Y_LEVEL, ConfigurationSettings.NICKEL_SPAWN_RARITY));
		}
		
		if (ConfigurationSettings.NICKEL_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.MAGNETITE.ordinal(), ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL, ConfigurationSettings.MAGNETITE_SPAWN_RARITY));
		}
	}

}
