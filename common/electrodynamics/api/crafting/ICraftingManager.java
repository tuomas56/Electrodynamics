package electrodynamics.api.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.api.crafting.util.TableRecipeType;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;

public interface ICraftingManager {

	/** Registers a table recipe. Stack-size is ignored */
	public void registerTableRecipe(TableRecipeType type, ItemStack input, ItemStack output, int damage);
	
	/** Registers a sieve recipe. Stack-size of input is ignored */
	public void registerSieveRecipe(ItemStack input, ArrayList<WeightedRecipeOutput> output, int duration);
	
	/** Registers a sintering oven recipe. Stack-size of input/output is assumed to be one <br />
	 *  Input and output size cannot exceed 9 */
	public void registerOvenRecipe(ArrayList<ItemStack> input, ArrayList<ItemStack> output, int duration);
	
}
