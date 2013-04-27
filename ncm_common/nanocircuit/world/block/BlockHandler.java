package nanocircuit.world.block;

import nanocircuit.world.block.item.ItemBlockOre;
import nanocircuit.world.core.WorldConfiguration;
import nanocircuit.world.lib.Ore;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block blockOre;
	
	public static void initializeBlocks() {
		blockOre = new BlockOre(WorldConfiguration.getBlockID("blockOre")).setUnlocalizedName("blockOre");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");
		for (int i=0; i<Ore.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(blockOre, 1, i), Ore.get(i).getLocalizedName("en_US"));
			MinecraftForge.setBlockHarvestLevel(blockOre, i, "pickaxe", Ore.get(i).harvestLevel);
		}
	}
	
}
