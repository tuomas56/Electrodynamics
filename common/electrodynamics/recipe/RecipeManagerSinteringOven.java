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
		RecipeSinteringOven recipe = getOvenRecipe(input);
		
		if (recipe == null) {
			input = trimItemStackList(input);
			if (input.size() == 1) {
				recipe = getFurnaceRecipe(input.get(0));
			}
		}
		
		return recipe;
	}
	
	public RecipeSinteringOven getOvenRecipe(List<ItemStack> input) {
		if (input == null) return null;
		
		for (RecipeSinteringOven recipe : sinteringOvenRecipes) {
			if (recipe.isInput(input)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public RecipeSinteringOven getFurnaceRecipe(ItemStack stack) {
		if (stack == null) return null;

		for (Entry<List<Integer>, ItemStack> recipe : FurnaceRecipes.smelting().getMetaSmeltingList().entrySet()) {
			ItemStack input = new ItemStack(recipe.getKey().get(0), 1, recipe.getKey().get(1));
			
			if (input.isItemEqual(stack)) {
				ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
				ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
				
				inputs.add(input);
				outputs.add(recipe.getValue());
				
				return new RecipeSinteringOven(inputs, outputs, 200);
			}
		}
		
		return null;
	}
	
	public List<ItemStack> trimItemStackList(List<ItemStack> input) {
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		
		for (ItemStack stack : input) {
			if (stack != null) {
				inputs.add(stack);
			}
		}
		
		return inputs;
	}
	
	public void initRecipes() {
		
	}
	
}
