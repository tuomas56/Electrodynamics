package electrodynamics.recipe.manager;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

import electrodynamics.recipe.RecipeGrinder;

public class RecipeManagerGrinder {

	public ArrayList<RecipeGrinder> grindingRecipes = new ArrayList<RecipeGrinder>();
	
	public void registerRecipe(RecipeGrinder recipe) {
		grindingRecipes.add(recipe);
	}

	public RecipeGrinder getRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeGrinder recipe : grindingRecipes) {
			if (recipe.isInput(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public void initRecipes() {
		
	}
	
}
