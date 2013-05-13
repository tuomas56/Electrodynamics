package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.Electrodynamics;
import electrodynamics.api.crafting.ICraftingManager;
import electrodynamics.api.crafting.util.TableRecipeType;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;

public class CraftingManager implements ICraftingManager {

	public RecipeManagerTable tableManager;
	public RecipeManagerSieve sieveManager;
	public RecipeManagerSinteringOven ovenManager;
	
	public static CraftingManager getInstance() {
		return Electrodynamics.instance.craftingManager;
	}
	
	@Override
	public void registerTableRecipe(TableRecipeType type, ItemStack input, ItemStack output, int damage) {
		tableManager.registerRecipe(type, input, output, damage);
	}
	
	@Override
	public void registerSieveRecipe(ItemStack input, ArrayList<WeightedRecipeOutput> output, int duration) {
		sieveManager.registerRecipe(new RecipeSieve(input, output, duration));
	}
	
	@Override
	public void registerOvenRecipe(ArrayList<ItemStack> input, ArrayList<ItemStack> output, int duration) {
		ovenManager.registerRecipe(input, output, duration);
	}
	
}
