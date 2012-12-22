package nanocircuit.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.block.material.Material;
import net.minecraft.item.crafting.FurnaceRecipes;

import nanocircuit.blocks.BlockManager;
import nanocircuit.compat.IC2;
import nanocircuit.core.Reference;
import nanocircuit.core.Config;

public class ItemManager 
{
	public static ItemComponent itemComponent;
	public static ItemCleanDoor itemDoor;
	public static ItemPCB itemPcb;
	
	public static void initItems()
	{
		itemComponent = new ItemComponent(Config.componentItemID);
		
		itemDoor = new ItemCleanDoor(Config.doorItemID);
		itemDoor.setItemName("doorClean");
		
		itemPcb = new ItemPCB(Config.pcbItemID);
		
		LanguageRegistry.instance().addStringLocalization("item.chunkMagnetite.name", "en_US", "Magnetite Chunk");
		LanguageRegistry.instance().addStringLocalization("item.dustMagnetite.name", "en_US", "Magnetite Dust");	
		LanguageRegistry.instance().addStringLocalization("item.dustLodestone.name", "en_US", "Lodestone Dust");
		LanguageRegistry.instance().addStringLocalization("item.ingotLodestone.name", "en_US", "Lodestone Ingot");	
		LanguageRegistry.instance().addStringLocalization("item.rodIron.name", "en_US", "Iron Rod");
		LanguageRegistry.instance().addStringLocalization("item.fanbladeIron.name", "en_US", "Iron Fanblade");	
		LanguageRegistry.instance().addStringLocalization("item.magnetFerrite.name", "en_US", "Ferrite Magnet");
		
		LanguageRegistry.instance().addStringLocalization("item.doorClean.name", "en_US", "Clean Door");
		
		LanguageRegistry.instance().addStringLocalization("item.basicPCB.name", "en_US", "Basic PCB");
	}
	
	public static void initRecipes()
	{
		ItemStack ironIngot = new ItemStack(Item.ingotIron, 1);
		ItemStack ironRod = new ItemStack(itemComponent, 1, Reference.ITEM_IRON_ROD_META);
		ItemStack ironFanblade = new ItemStack(itemComponent, 1, Reference.ITEM_IRON_FANBLADE_META);
		
		//IRON ROD RECIPE (user-friendly, can be crafted both ways
		GameRegistry.addRecipe(ironRod, "  I", " I ", "I  ", 'I', ironIngot);
		GameRegistry.addRecipe(ironRod, "I  ", " I ", "  I", 'I', ironIngot);
		
		//IRON FANBLADE RECIPE
		GameRegistry.addRecipe(ironFanblade, "I I", " R ", "I I", 'I', ironIngot, 'R', ironRod);
		
		//CLEAN DOOR RECIPE
		GameRegistry.addRecipe(new ItemStack(itemDoor), "IGI", "IGI", "IGI", 'I', ironIngot, 'G', new ItemStack(Block.glass));
		
		GameRegistry.addRecipe(new ItemStack(BlockManager.blockStorage, 1, Reference.BLOCK_STORAGE_LODESTONE_META), "III", "III", "III", 'I', new ItemStack(itemComponent, 1, Reference.ITEM_LODESTONE_INGOT_META));
		
		FurnaceRecipes.smelting().addSmelting(itemComponent.shiftedIndex, Reference.ITEM_LODESTONE_DUST_META, new ItemStack(itemComponent, 1, Reference.ITEM_LODESTONE_INGOT_META), 0.1f);
		
		//IC2 Compat - macerating magnetite chunks
		IC2.addMaceratorRecipe(new ItemStack(itemComponent, 1, Reference.ITEM_MAGNETITE_CHUNK_META), new ItemStack(itemComponent, 2, Reference.ITEM_MAGNETITE_DUST_META));
	}
}
