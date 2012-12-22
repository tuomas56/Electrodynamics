package nanocircuit.blocks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.src.ItemStack;

import nanocircuit.core.Reference;

public class BlockManager 
{
	public static BlockNCOre blockOre;
	
	public static void initBlocks()
	{
		blockOre = new BlockNCOre(500);
		GameRegistry.registerBlock(blockOre, nanocircuit.items.ItemNCOre.class);
		blockOre.setBlockName("ncmOre");
		MinecraftForge.setBlockHarvestLevel(blockOre, Reference.BLOCK_MAGNETITE_META, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockOre, Reference.BLOCK_NICKEL_META, "pickaxe", 2);
		OreDictionary.registerOre("oreMagnetite", new ItemStack(blockOre, 1, Reference.BLOCK_MAGNETITE_META));
		OreDictionary.registerOre("oreNickel", new ItemStack(blockOre, 1, Reference.BLOCK_NICKEL_META));
		
		LanguageRegistry.addName(new ItemStack(blockOre, 1, Reference.BLOCK_MAGNETITE_META), "Magnetite Ore");
		LanguageRegistry.addName(new ItemStack(blockOre, 1, Reference.BLOCK_NICKEL_META), "Nickel Ore");
		
	}
	
	public static void initRecipes()
	{	
	}
}
