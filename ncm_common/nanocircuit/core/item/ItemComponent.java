package nanocircuit.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nanocircuit.core.core.CreativeTabNCM;
import nanocircuit.core.lib.Component;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemComponent extends Item {
	
	private Icon[] textures;
	
	public ItemComponent(int id) {
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabNCM.item);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return textures[meta];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return Component.values()[stack.getItemDamage()].getUnlocalizedName();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for (int i=0; i<Component.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[Component.values().length];
		
		for (int i=0; i<Component.values().length; i++) {
			textures[i] = register.registerIcon(Component.values()[i].getTextureFile());
		}
	}
	
}
