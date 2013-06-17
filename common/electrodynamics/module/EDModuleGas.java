package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.block.BlockGas;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.block.Gas;

public class EDModuleGas extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockGasCO2 = new BlockGas(Gas.CO2);
		GameRegistry.registerBlock(EDBlocks.blockGasCO2, EDBlocks.blockGasCO2.getUnlocalizedName());
		
		EDBlocks.blockGasNatural = new BlockGas(Gas.NATURAL_UNREFINED);
		GameRegistry.registerBlock(EDBlocks.blockGasNatural, EDBlocks.blockGasNatural.getUnlocalizedName());
		
		EDBlocks.blockGasNitrogen = new BlockGas(Gas.N);
		GameRegistry.registerBlock(EDBlocks.blockGasNitrogen, EDBlocks.blockGasNitrogen.getUnlocalizedName());
	}

}
