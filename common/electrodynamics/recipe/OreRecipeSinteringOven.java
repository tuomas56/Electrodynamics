package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class OreRecipeSinteringOven extends RecipeSinteringOven {

	public ArrayList<String> itemInputs;
	
	public OreRecipeSinteringOven(ArrayList<String> itemInputs, ArrayList<ItemStack> itemOutputs, int processingTime) {
		super(null, itemOutputs, processingTime);
		
		this.itemInputs = itemInputs;
	}

	public boolean isInput(ArrayList<ItemStack> input) {
		//TODO
		return true;
	}
	
}
