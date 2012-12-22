package nanocircuit.blocks;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;

import nanocircuit.core.Reference;
import nanocircuit.core.Config;
import nanocircuit.items.ItemManager;
import nanocircuit.tile.TileConvectionOven;

public class BlockManager 
{
	public static BlockNCOre blockOre;
	public static BlockStorage blockStorage;
	public static BlockCleanDoor blockDoor;
	public static BlockCleanGlass blockGlass;
	public static BlockCleanIron blockIron;
	
	public static BlockConvectionOven blockOven;
	
	public static void initBlocks()
	{
		blockOre = new BlockNCOre(Config.oreBlockID);
		blockStorage = new BlockStorage(Config.storageBlockID);
		GameRegistry.registerBlock(blockOre, nanocircuit.items.ItemNCOre.class);
		GameRegistry.registerBlock(blockStorage, nanocircuit.items.ItemStorage.class);
		blockOre.setBlockName("tile.ncmOre");
		blockStorage.setBlockName("tile.ncmStorage");
		MinecraftForge.setBlockHarvestLevel(blockOre, Reference.BLOCK_MAGNETITE_META, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(blockOre, Reference.BLOCK_NICKEL_META, "pickaxe", 2);
		OreDictionary.registerOre("oreMagnetite", new ItemStack(blockOre, 1, Reference.BLOCK_MAGNETITE_META));
		OreDictionary.registerOre("oreNickel", new ItemStack(blockOre, 1, Reference.BLOCK_NICKEL_META));
		
		blockDoor = new BlockCleanDoor(Config.doorBlockID);
		GameRegistry.registerBlock(blockDoor);
		blockDoor.setBlockName("ncmDoor");
		
		blockGlass = new BlockCleanGlass(Config.glassBlockID);
		GameRegistry.registerBlock(blockGlass);
		blockGlass.setBlockName("ncmGlass");
		
		blockIron = new BlockCleanIron(Config.ironBlockID);
		GameRegistry.registerBlock(blockIron);
		blockIron.setBlockName("ncmIron");
		
		blockOven = new BlockConvectionOven(Config.machineBlockID);
		GameRegistry.registerBlock(blockOven);
		blockOven.setBlockName("ncmOven");
		
		LanguageRegistry.instance().addStringLocalization("tile.oreMagnetite.name", "en_US", "Magnetite Ore");
		LanguageRegistry.instance().addStringLocalization("tile.oreNickel.name", "en_US", "Nickel Ore");
		LanguageRegistry.instance().addStringLocalization("tile.strgLodestone.name", "en_US", "Lodestone Block");
		LanguageRegistry.instance().addStringLocalization("tile.ncmDoor.name", "en_US", "Clean Door");
		LanguageRegistry.instance().addStringLocalization("tile.ncmGlass.name", "en_US", "Clean Glass");
		LanguageRegistry.instance().addStringLocalization("tile.ncmIron.name", "en_US", "Clean Iron");
		LanguageRegistry.instance().addStringLocalization("tile.ncmOven.name", "en_US", "Convection Oven");
		
	}
	
	public static void initRecipes()
	{	
		GameRegistry.addShapelessRecipe(new ItemStack(ItemManager.itemComponent, 9, Reference.ITEM_LODESTONE_INGOT_META), new ItemStack(blockStorage, 1, Reference.BLOCK_STORAGE_LODESTONE_META));
	}
	
	public static void initTiles()
	{
		GameRegistry.registerTileEntity(TileConvectionOven.class, "TileConvectionOven");	
	}
}
