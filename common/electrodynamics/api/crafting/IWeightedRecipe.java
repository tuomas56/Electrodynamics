package electrodynamics.api.crafting;

import net.minecraft.item.ItemStack;

public abstract interface IWeightedRecipe {

	public abstract ItemStack getOutput();
	
	public abstract int getChance();
	
}
