package electrodynamics.item;

import java.util.List;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.item.Ingot;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemIngot extends Item {

	private Icon[] textures;
	
	public ItemIngot(int id) {
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabED.resource);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return textures[damage];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Ingot.get(stack.getItemDamage()).unlocalizedName + ".name";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (Ingot ingot : Ingot.values()) {
			list.add(new ItemStack(id, 1, ingot.ordinal()));
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[Ingot.values().length];
		
		for (int i=0; i<textures.length; i++) {
			textures[i] = register.registerIcon(Ingot.get(i).getTextureFile());
		}
	}
	
}
