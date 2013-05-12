package electrodynamics.item;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemLiquidLatex extends Item {

	private Icon texture;
	
	public ItemLiquidLatex(int id) {
		super(id);
		setCreativeTab(CreativeTabED.item);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "misc/liquidLatex");
	}
	
}
