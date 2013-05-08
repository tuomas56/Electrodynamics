package electrodynamics.recipe;

import java.util.ArrayList;
import java.util.Arrays;

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
	
}
