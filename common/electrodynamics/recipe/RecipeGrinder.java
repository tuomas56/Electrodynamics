package electrodynamics.recipe;

import java.util.ArrayList;

import electrodynamics.util.InventoryUtil;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class RecipeGrinder {

	public ItemStack input;
	
	public ArrayList<ItemStack> itemOutput;
	
	public LiquidStack liquidOutput;
	
	public RecipeGrinder(ItemStack input, ArrayList<ItemStack> itemOutput, LiquidStack liquidOutput) {
		this.input = input;
		this.itemOutput = itemOutput;
		this.liquidOutput = liquidOutput;
	}
	
	public boolean isInput(ItemStack stack) {
		return InventoryUtil.areItemStacksEqual(input, stack, false);
	}
	
}
