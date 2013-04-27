package nanocircuit.core.item;

import nanocircuit.core.core.CreativeTabNCM;
import net.minecraft.item.Item;
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

}
