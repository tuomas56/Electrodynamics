package electrodynamics.recipe;

import java.util.ArrayList;

import electrodynamics.lib.block.Ore;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Grinding;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipeManager {

	public static ArrayList<RecipeSmashingTable> smashingTableRecipes = new ArrayList<RecipeSmashingTable>();
	
	public static void registerSmashingTableRecipe(RecipeSmashingTable recipe) {
		smashingTableRecipes.add(recipe);
	}
	
	public static void registerSmashingTableRecipe(ItemStack in, ItemStack out, int damage) {
		smashingTableRecipes.add(new RecipeSmashingTable(in, out, damage));
	}
	
	public static RecipeSmashingTable getSmashingTableRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeSmashingTable recipe : smashingTableRecipes) {
			if (recipe.inputItem.isItemEqual(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public static void initializeSmashingRecipes() {
		/* Various vanilla recipes */
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.stone), new ItemStack(Block.cobblestone), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.brick), new ItemStack(Item.brick, 4, 0), 1);
		
		//First 6 ore/grinding ordinals are equal
		for (int i=0; i<6; i++) {
			RecipeManager.registerSmashingTableRecipe(Ore.get(i).toItemStack(), Grinding.get(i).toItemStack(), 1);
		}
		
		//Various other item smash recipes
		RecipeManager.registerSmashingTableRecipe(Component.LITHIUM_CLAY.toItemStack(), Grinding.LITHIUM.toItemStack(), 1);
		
		//Vanilla ores
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreDiamond), Grinding.DIAMOND.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreEmerald), Grinding.EMERALD.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreGold), Grinding.GOLD.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreIron), Grinding.IRON.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreLapis), Grinding.LAPIS.toItemStack(), 1);
		RecipeManager.registerSmashingTableRecipe(new ItemStack(Block.oreRedstone), Grinding.REDSTONE.toItemStack(), 1);
	}
	
}
