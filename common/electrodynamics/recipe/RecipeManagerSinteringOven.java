package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeManagerSinteringOven {

	public ArrayList<RecipeSinteringOven> sinteringOvenRecipes = new ArrayList<RecipeSinteringOven>();
	
	public void registerRecipe(RecipeSinteringOven recipe) {
		sinteringOvenRecipes.add(recipe);
	}
	
	public void registerRecipe(ArrayList<ItemStack> input, ArrayList<ItemStack> output, int duration) {
		this.registerRecipe(new RecipeSinteringOven(input, output, duration));
	}
	
}
