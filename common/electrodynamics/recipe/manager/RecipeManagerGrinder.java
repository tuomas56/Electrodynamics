package electrodynamics.recipe.manager;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.fluids.FluidRegistry.FluidRegisterEvent;
import net.minecraftforge.liquids.LiquidDictionary;
import electrodynamics.recipe.RecipeGrinder;

public class RecipeManagerGrinder {

	public ArrayList<RecipeGrinder> grindingRecipes = new ArrayList<RecipeGrinder>();
	
	public void registerRecipe(RecipeGrinder recipe) {
		grindingRecipes.add(recipe);
	}

	public RecipeGrinder getRecipe(ItemStack in) {
		if (in == null) return null;
		
		for (RecipeGrinder recipe : grindingRecipes) {
			if (recipe.isInput(in)) {
				return recipe;
			}
		}
		
		return null;
	}
	
	public void initRecipes() {
		registerRecipe(new RecipeGrinder(new ItemStack(Block.cactus), Arrays.asList(new ItemStack[] {new ItemStack(Item.dyePowder)}), new FluidStack(FluidRegistry.WATER, 25)));
	}
	
}
