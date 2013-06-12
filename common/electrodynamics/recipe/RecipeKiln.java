package electrodynamics.recipe;


import electrodynamics.util.ItemUtil;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipeKiln {

	public static final int DEFAULT_PROCESSING_TIME = 20;

	private List<ItemStack> inputs;
	private List<ItemStack> outputs;
	public int processingTime = DEFAULT_PROCESSING_TIME;

	public RecipeKiln(List<ItemStack> inputs, List<ItemStack> outputs) {
		this.inputs = inputs;
		this.outputs = outputs;
	}

	public RecipeKiln(List<ItemStack> inputs, List<ItemStack> outputs, int processingTime) {
		this( inputs, outputs );
		this.processingTime = processingTime;
	}

	// if all the items required for this recipes are found.
	public boolean matchesInput(List<ItemStack> itemsList) {
		itemsList = ItemUtil.trimItemsList( itemsList, false );
		if( itemsList == null || itemsList.isEmpty() )
			return false;

		List<ItemStack> inputsCopy = ItemUtil.trimItemsList( this.inputs, false );
		for( ItemStack input : inputsCopy ) {
			int index = ItemUtil.getIndexOf( input, itemsList, false );
			if( index == -1 )
				return false;
			if( input.stackSize > 1 && itemsList.get( index ).stackSize < input.stackSize )
				return false;
		}
		return true;
	}

	public List<ItemStack> getInputs() {
		return Collections.unmodifiableList( this.inputs );
	}

	public List<ItemStack> getOutputs() {
		return Collections.unmodifiableList( this.outputs );
	}

	public float getMultiplier(List<ItemStack> itemStackList) {
		itemStackList = ItemUtil.trimItemsList( itemStackList, false );
		float retValue = Integer.MAX_VALUE;
		for( ItemStack input : this.inputs ) {
			int index = ItemUtil.getIndexOf( input, itemStackList, false );
			ItemStack stack = itemStackList.get( index );
			retValue = Math.min( (float) stack.stackSize / input.stackSize, retValue );
		}
		return retValue;
	}

	public static RecipeKiln mergeRecipes(RecipeKiln... recipes) {
		List<ItemStack> input = new ArrayList<ItemStack>();
		List<ItemStack> output = new ArrayList<ItemStack>();
		int processingTime = 0;

		for( RecipeKiln current : recipes ) {
			if( current != null ) {
				processingTime += current.processingTime;
				input.addAll( current.getInputs() );
				output.addAll( current.getOutputs() );
			}
		}

		if( !input.isEmpty() )
			return new RecipeKiln( input, output, processingTime );
		return null;
	}


}
