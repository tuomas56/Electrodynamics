package nanocircuit.compat;

import net.minecraft.src.ItemStack;

import nanocircuit.core.Reflect;

public class IC2 {
	
	public static void addMaceratorRecipe(ItemStack input, ItemStack output)
	{
		Reflect.callMethod("ic2.api.Ic2Recipes", "addMaceratorRecipe", new Class[]{ItemStack.class, ItemStack.class}, new Object[]{input, output});
	}

}
