package electrodynamics.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.block.Ore;
import electrodynamics.lib.item.ItemIDs;

public class CreativeTabED extends CreativeTabs {

	public static CreativeTabs item;
	public static CreativeTabs block;
	
	public int itemID = 1;
	public int itemMeta = 0;
	
	static {
		item = new CreativeTabED("ED Items").setIcon(ItemIDs.ITEM_PCB_ID + 256, 0);
		block = new CreativeTabED("ED Blocks").setIcon(BlockIDs.BLOCK_ORE_ID, Ore.NICKEL.ordinal());
	}
	
	public CreativeTabED(String label) {
		super(label.toLowerCase().replace(" ", "_"));
		LanguageRegistry.instance().addStringLocalization("itemGroup."+label.toLowerCase().replace(" ", "_"), label);
	}
	
	public CreativeTabED setIcon(int id, int meta) {
		this.itemID = id;
		this.itemMeta = meta;
		return this;
	}
	
	public CreativeTabED setIcon(ItemStack stack) {
		this.itemID = stack.itemID;
		this.itemMeta = stack.getItemDamage();
		return this;
	}
	
	public ItemStack getIconItemStack() {
		return new ItemStack(itemID, 1, itemMeta);
	}
	
}
