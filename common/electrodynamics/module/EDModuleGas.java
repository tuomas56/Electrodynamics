package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.block.BlockGas;
import electrodynamics.block.EDBlocks;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.Strings;

public class EDModuleGas extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockGas = new BlockGas(BlockIDs.BLOCK_GAS_ID).setUnlocalizedName(Strings.GAS_NATURAL_UNREFINED);
		GameRegistry.registerBlock(EDBlocks.blockGas, Strings.BLOCK_GAS);
		EDLanguage.getInstance().registerBlock(EDBlocks.blockGas);
	}

}
