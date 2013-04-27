package nanocircuit.world;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlock {
	public ItemOre(int i) {
		super( i );
		setHasSubtypes( true );
		setUnlocalizedName( "ncOre" );
	}

	public int getMetadata(int metadata) {
		return metadata;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return Ores.get( itemstack.getItemDamage() ).getUnlocalizedName();
	}
}
