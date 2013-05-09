package electrodynamics.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import electrodynamics.api.crafting.util.WeightedRecipeOutput;

import net.minecraft.item.ItemStack;

public class RecipeBasicSieve {

	public ItemStack itemInput;
	
	public ArrayList<WeightedRecipeOutput> itemOutputs;
	
	public int processingTime;
	
	public RecipeBasicSieve(ItemStack itemInput, ArrayList<WeightedRecipeOutput> itemOutputs, int processingTime) {
		this.itemInput = itemInput;
		this.itemOutputs = itemOutputs;
		this.processingTime = processingTime;
	}
	
	public RecipeBasicSieve(ItemStack itemInput, int processingTime, WeightedRecipeOutput ... itemOutputs) {
		this(itemInput, new ArrayList<WeightedRecipeOutput>(Arrays.asList(itemOutputs)), processingTime);
	}
	
	public RecipeBasicSieve(ItemStack itemInput, int processingTime) {
		this.itemInput = itemInput;
		this.processingTime = processingTime;
		this.itemOutputs = new ArrayList<WeightedRecipeOutput>();
		
		setOutput(this.itemOutputs);
	}
	
	public void setOutput(ArrayList<WeightedRecipeOutput> outputs) {

	}

	public boolean isInput(ItemStack input) {
		return (ItemStack.areItemStacksEqual(input, itemInput));
	}
	
}
