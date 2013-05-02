package electrodynamics.core.block;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.core.lib.BlockIDs;
import electrodynamics.core.lib.Strings;

public class CoreBlockHandler {

	public static Block blockHoloPad;
	
	public static void initializeBlocks() {
		blockHoloPad = new BlockHoloPad(BlockIDs.BLOCK_HOLO_PAD_ID).setUnlocalizedName(Strings.BLOCK_HOLO_PAD_NAME);
		GameRegistry.registerBlock(blockHoloPad, Strings.BLOCK_HOLO_PAD_NAME);
		LanguageRegistry.addName(blockHoloPad, "Holo Pad");
	}
	
}
