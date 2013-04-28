package nanocircuit.core.core;

import nanocircuit.core.lib.BlockIDs;
import nanocircuit.core.lib.ItemIDs;
import nanocircuit.world.lib.Ore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CreativeTabNCM extends CreativeTabs {

	public static CreativeTabs item;
	public static CreativeTabs block;
	
	public int itemID = 1;
	public int itemMeta = 0;
	
	static {
		item = new CreativeTabNCM("NCM Items").setIcon(ItemIDs.ITEM_PCB_ID + 256, 0);
		block = new CreativeTabNCM("NCM Blocks").setIcon(BlockIDs.BLOCK_ORE_ID, Ore.NICKEL.ordinal());
	}
	
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
