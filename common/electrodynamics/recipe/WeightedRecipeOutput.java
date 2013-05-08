package electrodynamics.recipe;

import net.minecraft.item.ItemStack;

public class WeightedRecipeOutput {

	public ItemStack output;
	
	public float chance;
	
	/**
	 * Allows for machines to have a random chance of producing items
	 * @param output ItemStack to output
	 * @param chance Chance of output. Value is a float from 0 - 100
	 */
	public WeightedRecipeOutput(ItemStack output, float chance) {
		this.output = output;
		this.chance = chance;
		
		if (chance < 0) chance = 0F;
		if (chance > 100) chance = 100F;
	}
	
}
