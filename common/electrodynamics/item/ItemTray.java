package electrodynamics.item;

import electrodynamics.core.CreativeTabED;
import net.minecraft.item.Item;

public class ItemTray extends Item {

	public ItemTray(int id) {
		super(id);
		setMaxStackSize(64);
		setMaxDamage(0);
		setCreativeTab(CreativeTabED.tool);
	}
	
}
