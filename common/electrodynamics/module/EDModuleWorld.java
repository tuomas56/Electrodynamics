package electrodynamics.module;

import java.util.EnumSet;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.block.BlockHandler;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Ore;
import electrodynamics.module.ModuleManager.Module;
import electrodynamics.world.WorldGenOre;

public class EDModuleWorld extends EDModule {

	@Override
	public void preInit() {
		BlockHandler.createBlockOre();
	}

	@Override
	public void init() {
		if (ConfigurationSettings.MAGNETITE_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.NICKEL.ordinal(), ConfigurationSettings.NICKEL_SPAWN_AMOUNT, ConfigurationSettings.NICKEL_MAX_Y_LEVEL, ConfigurationSettings.NICKEL_SPAWN_RARITY));
		}
		
		if (ConfigurationSettings.NICKEL_ENABLED) {
			GameRegistry.registerWorldGenerator(new WorldGenOre(BlockIDs.BLOCK_ORE_ID, Ore.MAGNETITE.ordinal(), ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL, ConfigurationSettings.MAGNETITE_SPAWN_RARITY));
		}
	}

	@Override
	public void postInit() {
		
	}

	@Override
	public EnumSet<Module> dependencies() {
		return EnumSet.of(Module.CORE);
	}

	@Override
	public boolean canLoad() {
		return true;
	}

	@Override
	public String failLoadReason() {
		return "";
	}

}
