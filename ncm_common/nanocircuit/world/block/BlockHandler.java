package nanocircuit.world.block;

import nanocircuit.core.lib.BlockIDs;
import nanocircuit.core.lib.Strings;
import nanocircuit.world.block.item.ItemBlockOre;
import nanocircuit.world.lib.Ore;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block blockOre;
	
	public static void initializeBlocks() {
		blockOre = new BlockOre(BlockIDs.BLOCK_ORE_ID).setUnlocalizedName(Strings.BLOCK_ORE_NAME);
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, Strings.BLOCK_ORE_NAME);
		for (int i=0; i<Ore.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(blockOre, 1, i), Ore.get(i).getLocalizedName("en_US"));
			MinecraftForge.setBlockHarvestLevel(blockOre, i, "pickaxe", Ore.get(i).harvestLevel);
		}
	}
	
}
