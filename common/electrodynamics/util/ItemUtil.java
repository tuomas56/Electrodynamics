package electrodynamics.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

	public static ItemStack getAndResize(ItemStack stack, int amount) {
		ItemStack stack2 = stack.copy();
		stack2.stackSize = amount;
		return stack2;
	}
	
	public static int getFuelValue(ItemStack stack) {
		if (stack == null) return 0;
		return Math.max(TileEntityFurnace.getItemBurnTime(stack), GameRegistry.getFuelValue(stack));
	}

	public static List<ItemStack> trimItemsList(List<ItemStack> itemsList, boolean ignoreNBT) {
		List<ItemStack> result = new ArrayList<ItemStack>();
		for( ItemStack current : itemsList ) {
			if( current == null )
				continue;

			int index;
			boolean contained = false;
			for(index = 0; index < result.size(); index++) {
				ItemStack s = result.get( index );
				if( current.itemID == s.itemID && current.getItemDamage() == s.getItemDamage() ) {
					if( ignoreNBT || ItemStack.areItemStackTagsEqual( current, s ) ) {
						contained = true;
						break;
					}
				}
			}

			if( contained ) {
				result.get( index ).stackSize += current.stackSize;
			} else {
				result.add( current.copy() );
			}
		}
		return result;
	}

}
