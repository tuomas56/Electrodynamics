package electrodynamics.recipe;

import net.minecraft.item.ItemStack;

public class RecipeSmashingTable {

	public ItemStack inputItem;
	public ItemStack outputItem;
	
	public int hammerDamage;
	
	public RecipeSmashingTable(ItemStack in, ItemStack out, int damage) {
		this.inputItem = in;
		this.outputItem = out;
		this.hammerDamage = damage;
	}
	
	public boolean isInput(ItemStack input) {
		return ItemStack.areItemStacksEqual(input, inputItem);
	}
	
	/** Extra code to be called when the item is smashed. Can be left blank */
	public void onSmashed() {}
	
}
