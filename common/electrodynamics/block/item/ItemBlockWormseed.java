package electrodynamics.block.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.Strings;

public class ItemBlockWormseed extends ItemBlock {
	
	public ItemBlockWormseed(int i) {
		super(i);
		setHasSubtypes(true);
	}

	public int getMetadata(int metadata) {
		return metadata;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return Strings.BLOCK_WORMSEED;
	}
	
}
