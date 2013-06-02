package electrodynamics.item.hammer;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import electrodynamics.lib.core.ModInfo;

public class ItemStoneHammer extends ItemHammer {

	public static Icon texture;
	
	public ItemStoneHammer(int id) {
		super(id, 50);
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
