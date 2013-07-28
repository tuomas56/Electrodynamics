package electrodynamics.block.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import electrodynamics.lib.block.Decorative;

public class ItemBlockDecorative extends ItemBlock {
	
	public ItemBlockDecorative(int i) {
		super(i);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return Decorative.get(itemstack.getItemDamage()).unlocalizedName;
	}
	
}
