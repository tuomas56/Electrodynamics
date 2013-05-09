package electrodynamics.api.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;

public interface ICraftingManager {

	/** Registers a smashing table recipe. Stack-size is ignored */
	public void registerSmashingTableRecipe(ItemStack input, ItemStack output, int damage);
	
	/** Registers a sieve recipe. Stack-size of input is ignored */
	public void registerSieveRecipe(ItemStack input, ArrayList<WeightedRecipeOutput> output, int duration);
	
}
