package electrodynamics.recipe;

import electrodynamics.api.tool.ITool;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreRecipeTable extends RecipeTable {

	public String oreDictionaryName;
	
	public OreRecipeTable(TableRecipeType type, String in, ItemStack out, int damage) {
		super(type, null, out, damage);
		
		this.oreDictionaryName = in;
	}

	@Override
	public boolean isInput(ItemStack input, ItemStack tool) {
		for (ItemStack stack : (OreDictionary.getOres(oreDictionaryName))) {
			if (input.isItemEqual(stack) && ((ITool)tool.getItem()).getToolType() == type.requiredTool) {
				return true;
			}
		}
		
		return false;
	}
	
}
