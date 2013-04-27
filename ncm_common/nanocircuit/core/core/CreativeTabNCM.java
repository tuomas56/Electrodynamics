package nanocircuit.core.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CreativeTabNCM extends CreativeTabs {

	public static CreativeTabs item = new CreativeTabNCM("NCM Items");
	public static CreativeTabs block = new CreativeTabNCM("NCM Blocks");
	
	public int itemID = 1;
	public int itemMeta = 0;
	
	public CreativeTabNCM(String label) {
		super(label.toLowerCase().replace(" ", "_"));
		LanguageRegistry.instance().addStringLocalization("itemGroup."+label.toLowerCase().replace(" ", "_"), label);
	}
	
	public CreativeTabNCM setIcon(int id, int meta) {
		this.itemID = id;
		this.itemMeta = meta;
		return this;
	}
	
	public CreativeTabNCM setIcon(ItemStack stack) {
		this.itemID = stack.itemID;
		this.itemMeta = stack.getItemDamage();
		return this;
	}
	
	public ItemStack getIconItemStack() {
		return new ItemStack(itemID, 1, itemMeta);
	}
	
}
