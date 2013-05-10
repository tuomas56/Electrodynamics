package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.block.BlockGas;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.item.ItemBlockGas;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Gas;
import electrodynamics.lib.core.Strings;

public class EDModuleGas extends EDModule {

	@Override
	public void preInit() {
		EDBlocks.blockGas = new BlockGas(BlockIDs.BLOCK_GAS_ID).setUnlocalizedName(Strings.BLOCK_GAS);
		GameRegistry.registerBlock(EDBlocks.blockGas, ItemBlockGas.class, Strings.BLOCK_GAS);
		for (Gas gas : Gas.values()) {
			LanguageRegistry.addName(gas.toItemStack(), gas.localizedName);
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {
		
	}
	
}
