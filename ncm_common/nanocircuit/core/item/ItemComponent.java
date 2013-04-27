package nanocircuit.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nanocircuit.core.core.CreativeTabNCM;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemComponent extends Item {
	
	public ItemComponent(int id) {
		super(id);
		setHasSubtypes(true);
		setCreativeTab(CreativeTabNCM.item);
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		//TODO Rewrite - dmillerw
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		//TODO Rewrite - dmillerw
		return "";
	}

}
