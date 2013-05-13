package electrodynamics.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeSinteringOven {

	public ArrayList<ItemStack> itemInputs;
	public ArrayList<ItemStack> itemOutputs;
	
	public int processingTime;
	
	public RecipeSinteringOven(ArrayList<ItemStack> itemInput, ArrayList<ItemStack> itemOutputs, int processingTime) {
		this.itemInputs = itemInput;
		this.itemOutputs = itemOutputs;
		this.processingTime = processingTime;
	}
	
	public RecipeSinteringOven(int processingTime) {
		this.processingTime = processingTime;
		this.itemInputs = new ArrayList<ItemStack>();
		this.itemOutputs = new ArrayList<ItemStack>();
		
		setInput(itemInputs);
		setOutput(this.itemOutputs);
	}

	public void setInput(ArrayList<ItemStack> inputs) {
		
	}
	
	public void setOutput(ArrayList<ItemStack> outputs) {

	}

	public boolean isInput(ArrayList<ItemStack> input) {
		for (ItemStack stack : input) {
			if (doesListContain(stack)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean doesListContain(ItemStack stack) {
		for (ItemStack stack2 : this.itemInputs) {
			if (stack2.isItemEqual(stack)) {
				return true;
			}
		}
		
		return false;
	}
	
}
