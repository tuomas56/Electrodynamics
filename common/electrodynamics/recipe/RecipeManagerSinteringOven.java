package electrodynamics.recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class RecipeManagerSinteringOven {

	public ArrayList<RecipeSinteringOven> sinteringOvenRecipes = new ArrayList<RecipeSinteringOven>();
	
	public void registerRecipe(RecipeSinteringOven recipe) {
		sinteringOvenRecipes.add(recipe);
	}
	
	public void registerRecipe(List<ItemStack> input, List<ItemStack> output, int duration) {
		this.registerRecipe(new RecipeSinteringOven(input, output, duration));
	}
	
	public RecipeSinteringOven getRecipe(List<ItemStack> input) {
		if (input == null) return null;
		
		for (RecipeSinteringOven recipe : sinteringOvenRecipes) {
			if (recipe.isInput(input)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public void initRecipes() {
		for (Entry<List<Integer>, ItemStack> recipe : FurnaceRecipes.smelting().getMetaSmeltingList().entrySet()) {
			ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
			ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
			
			inputs.add(new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1)));
			outputs.add(recipe.getValue());
			
			registerRecipe(inputs, outputs, 200);
		}
	}
	
}
