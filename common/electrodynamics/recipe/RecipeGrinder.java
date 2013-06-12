package electrodynamics.recipe;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;
import electrodynamics.util.ItemUtil;

public class RecipeGrinder {

	public ItemStack input;
	
	public List<ItemStack> itemOutput;
	
	public LiquidStack liquidOutput;
	
	public RecipeGrinder(ItemStack input, List<ItemStack> itemOutput, LiquidStack liquidOutput) {
		this.input = input;
		this.itemOutput = itemOutput;
		this.liquidOutput = liquidOutput;
	}
	
	public boolean isInput(ItemStack stack) {
		return ItemUtil.areItemStacksEqual(input, stack, false);
	}
	
}
