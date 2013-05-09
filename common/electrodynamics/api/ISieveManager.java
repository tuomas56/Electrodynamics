package electrodynamics.api;

import electrodynamics.recipe.RecipeBasicSieve;

public abstract interface ISieveManager {

	public abstract void registerSieveRecipe(RecipeBasicSieve recipe);
	
}
