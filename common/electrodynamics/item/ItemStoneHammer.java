package electrodynamics.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.ModInfo;

public class ItemStoneHammer extends Item {

	private Icon texture;
	
	public ItemStoneHammer(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(CreativeTabED.item);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/stoneHammer");
	}
	
}
