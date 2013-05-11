package electrodynamics.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class ItemPeelingSpud extends Item {

	private Icon texture;
	
	public ItemPeelingSpud(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(100);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/peelingSpud");
	}
	
}
