package electrodynamics.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import electrodynamics.api.crafting.util.WeightedRecipeOutput;

public class OreRecipeBasicSieve extends RecipeSieve {

	public String oreDictionaryName;
	
	public OreRecipeBasicSieve(String itemInput, ArrayList<WeightedRecipeOutput> itemOutputs, int processingTime) {
		super(null, itemOutputs, processingTime);
		
		this.oreDictionaryName = itemInput;
	}
	
	public OreRecipeBasicSieve(String itemInput, int processingTime, WeightedRecipeOutput ... itemOutputs) {
		super(null, new ArrayList<WeightedRecipeOutput>(Arrays.asList(itemOutputs)), processingTime);
		
		this.oreDictionaryName = itemInput;
	}
	
	public OreRecipeBasicSieve(String itemInput, int processingTime) {
		super(null, processingTime);
		
		this.oreDictionaryName = itemInput;
	}
	
	@Override
	public boolean isInput(ItemStack input) {
		for (ItemStack stack : (OreDictionary.getOres(oreDictionaryName))) {
			if (input.isItemEqual(stack)) {
				return true;
			}
		}
		
		return false;
	}
	
}
