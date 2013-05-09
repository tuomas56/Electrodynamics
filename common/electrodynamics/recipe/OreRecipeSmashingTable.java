package electrodynamics.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreRecipeSmashingTable extends RecipeSmashingTable {

	public String oreDictionaryName;
	
	public OreRecipeSmashingTable(String in, ItemStack out, int damage) {
		super(null, out, damage);
		
		this.oreDictionaryName = in;
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
