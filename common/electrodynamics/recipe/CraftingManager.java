package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import electrodynamics.Electrodynamics;
import electrodynamics.api.crafting.ICraftingManager;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;

public class CraftingManager implements ICraftingManager {

	public SmashingManager smashingManager;
	public SieveManager sieveManager;
	
	public static CraftingManager getInstance() {
		return Electrodynamics.instance.craftingManager;
	}
	
	@Override
	public void registerSmashingTableRecipe(ItemStack input, ItemStack output, int damage) {
		smashingManager.registerRecipe(input, output, damage);
	}
	
	@Override
	public void registerSieveRecipe(ItemStack input, ArrayList<WeightedRecipeOutput> output, int duration) {
		sieveManager.registerRecipe(new RecipeBasicSieve(input, output, duration));
	}
	
}
