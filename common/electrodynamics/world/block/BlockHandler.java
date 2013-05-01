package electrodynamics.world.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.core.lib.BlockIDs;
import electrodynamics.core.lib.Strings;
import electrodynamics.world.block.item.ItemBlockOre;
import electrodynamics.world.lib.Ore;

public class BlockHandler {

	public static Block blockOre;
	
	public static void initializeBlocks() {
		blockOre = new BlockOre(BlockIDs.BLOCK_ORE_ID).setUnlocalizedName(Strings.BLOCK_ORE_NAME);
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, Strings.BLOCK_ORE_NAME);
		for (int i=0; i<Ore.values().length; i++) {
			ItemStack ore = new ItemStack(blockOre, 1, i);
			
			LanguageRegistry.addName(ore, Ore.get(i).getLocalizedName("en_US"));
			MinecraftForge.setBlockHarvestLevel(blockOre, i, "pickaxe", Ore.get(i).harvestLevel);
			OreDictionary.registerOre(Ore.get(i).oreDictionaryName, ore);
		}
	}
	
}
