package electrodynamics.api.crafting.util;

import java.util.Random;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.Dust;

public class WeightedRecipeOutput {

	public ItemStack output;
	
	public float chance;
	
	/**
	 * Allows for machines to have a random chance of producing items
	 * @param output ItemStack to output
	 * @param chance Chance of output. Value is an int from 0 - 1
	 */
	public WeightedRecipeOutput(ItemStack output, float chance) {
		this.output = output;
		this.chance = chance;
		
		if (chance < 0) chance = 0;
		if (chance > 1) chance = 1;
	}
	
	public WeightedRecipeOutput(Dust output, float chance) {
		this(output.toItemStack(), chance);
	}
	
	public WeightedRecipeOutput(Component output, float chance) {
		this(output.toItemStack(), chance);
	}
	
	public boolean willOutput(Random random) {
		return (random.nextFloat() <= this.chance);
	}

}
