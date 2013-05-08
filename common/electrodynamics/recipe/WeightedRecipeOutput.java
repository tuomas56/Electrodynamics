package electrodynamics.recipe;

import java.util.Random;

import net.minecraft.item.ItemStack;

public class WeightedRecipeOutput {

	public ItemStack output;
	
	public int chance;
	
	/**
	 * Allows for machines to have a random chance of producing items
	 * @param output ItemStack to output
	 * @param chance Chance of output. Value is an int from 0 - 100
	 */
	public WeightedRecipeOutput(ItemStack output, int chance) {
		this.output = output;
		this.chance = chance;
		
		if (chance < 0) chance = 0;
		if (chance > 100) chance = 100;
	}
	
	public boolean willOutput(Random random) {
		return (random.nextInt(100) <= this.chance);
	}
	
}
