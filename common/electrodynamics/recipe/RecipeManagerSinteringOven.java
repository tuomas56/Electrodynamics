package electrodynamics.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import electrodynamics.lib.item.Ingot;

public class RecipeManagerSinteringOven {

	public ArrayList<RecipeSinteringOven> sinteringOvenRecipes = new ArrayList<RecipeSinteringOven>();
	
	public void registerRecipe(RecipeSinteringOven recipe) {
		sinteringOvenRecipes.add(recipe);
	}
	
	public void registerRecipe(ArrayList<ItemStack> input, ArrayList<ItemStack> output, int duration) {
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
		registerRecipe(new RecipeSinteringOven(1) {
			@Override
			public void setInput(List<ItemStack> inputs) {
				inputs.add(new ItemStack(Item.coal, 1, Short.MAX_VALUE));
				inputs.add(new ItemStack(Item.ingotIron));
			}
			
			@Override
			public void setOutput(List<ItemStack> outputs) {
				outputs.add(Ingot.STEEL.toItemStack());
			}
		});
	}
	
}
