package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.block.BlockGas;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Gas;

public class EDModuleGas extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockGasCO2 = new BlockGas(BlockIDs.BLOCK_GAS_CO2_ID, Gas.CO2);
		EDBlocks.blockGasNatural = new BlockGas(BlockIDs.BLOCK_GAS_NATURAL_ID, Gas.NATURAL_UNREFINED);
		EDBlocks.blockGasNitrogen = new BlockGas(BlockIDs.BLOCK_GAS_NITROGEN_ID, Gas.N);
	}

}
