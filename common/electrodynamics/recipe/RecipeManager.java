package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeManager {

	public static ArrayList<RecipeSmashingTable> smashingTableRecipes = new ArrayList<RecipeSmashingTable>();
	
	public static void registerSmashingTableRecipe(RecipeSmashingTable recipe) {
		smashingTableRecipes.add(recipe);
	}
	
	public static void registerSmashingTableRecipe(ItemStack in, ItemStack out, int damage) {
		smashingTableRecipes.add(new RecipeSmashingTable(in, out, damage));
	}
	
	public static RecipeSmashingTable getRecipe(ItemStack in) {
		for (RecipeSmashingTable recipe : smashingTableRecipes) {
			if (recipe.inputItem.isItemEqual(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
}
