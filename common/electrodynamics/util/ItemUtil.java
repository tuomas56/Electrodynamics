package electrodynamics.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ItemUtil {

	public static int getFuelValue(ItemStack stack) {
		if (stack == null) return 0;
		return Math.max(TileEntityFurnace.getItemBurnTime(stack), GameRegistry.getFuelValue(stack));
	}
	
}
