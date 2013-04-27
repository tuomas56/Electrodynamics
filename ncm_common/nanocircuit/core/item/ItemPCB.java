package nanocircuit.core.item;

import nanocircuit.core.core.CreativeTabNCM;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemPCB extends Item {
	
	public ItemPCB(int id) {
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabNCM.item);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return null;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		//TODO Rewrite - dmillerw
		return itemstack.getItemName();
	}

}
