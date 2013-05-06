package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockLaserEmitter;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Strings;

public class EDModuleLaser extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockLaserEmitter = new BlockLaserEmitter(BlockIDs.BLOCK_LASER_EMITTER_ID).setUnlocalizedName(Strings.BLOCK_LASER_EMITTER);
		GameRegistry.registerBlock(EDBlocks.blockLaserEmitter, Strings.BLOCK_LASER_EMITTER);
		LanguageRegistry.addName(EDBlocks.blockLaserEmitter, "Laser Emitter");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
