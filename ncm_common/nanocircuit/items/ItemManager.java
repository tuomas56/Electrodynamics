package nanocircuit.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import nanocircuit.compat.IC2;
import nanocircuit.core.Reference;

public class ItemManager 
{
	public static ItemComponent itemComponent;
	
	public static void initItems()
	{
		itemComponent = new ItemComponent(1500);
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_MAGNETITE_CHUNK_META), "Magnetite Chunk");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_MAGNETITE_DUST_META), "Magnetite Dust");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_LODESTONE_DUST_META), "Lodestone Dust");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_LODESTONE_INGOT_META), "Lodestone Ingot");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_IRON_ROD_META), "Iron Rod");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_IRON_FANBLADE_META), "Iron Fanblade");
		LanguageRegistry.addName(new ItemStack(itemComponent, 1, Reference.ITEM_FERRITE_MAGNET_META), "Ferrite Magnet");
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
		
		//IC2 Compat - macerating magnetite chunks
		IC2.addMaceratorRecipe(new ItemStack(itemComponent, 1, Reference.ITEM_MAGNETITE_CHUNK_META), new ItemStack(itemComponent, 2, Reference.ITEM_MAGNETITE_DUST_META));
	}
}
