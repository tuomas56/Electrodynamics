package electrodynamics.api.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;
import electrodynamics.recipe.TableRecipeType;

public interface ICraftingManager {

	/** Registers a table recipe. Stack-size is ignored */
	public void registerTableRecipe(TableRecipeType type, ItemStack input, ItemStack output, int damage);
	
	/** Registers a sieve recipe. Stack-size of input is ignored */
	public void registerSieveRecipe(ItemStack input, ArrayList<WeightedRecipeOutput> output, int duration);
	
}
