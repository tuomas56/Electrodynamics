package nanocircuit.world.block.item;

import nanocircuit.world.lib.Ore;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockOre extends ItemBlock {
	public ItemBlockOre(int i) {
		super(i);
		setHasSubtypes(true);
		setUnlocalizedName("ncOre");
	}

	public int getMetadata(int metadata) {
		return metadata;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return Ore.get(itemstack.getItemDamage()).getUnlocalizedName();
	}
}
