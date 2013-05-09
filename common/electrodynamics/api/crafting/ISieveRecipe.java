package electrodynamics.api.crafting;

import net.minecraft.item.ItemStack;
import electrodynamics.recipe.WeightedRecipeOutput;

public abstract interface ISieveRecipe {

	public abstract ItemStack getInput();
	
	public abstract WeightedRecipeOutput[] getOutputs();
	
	public int getDuration();
	
}
