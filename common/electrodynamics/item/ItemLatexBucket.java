package electrodynamics.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class ItemLatexBucket extends Item {

	private Icon texture;
	
	public ItemLatexBucket(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabED.resource);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "misc/bucketLatex");
	}
	
}
