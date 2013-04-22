package nanocircuit.world;

import nanocircuit.core.Reference;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemOre extends ItemBlock {
	public ItemOre(int i) {
		super( i );
		setHasSubtypes( true );
		setUnlocalizedName( "ncOre" );
	}

	public int getPlacedBlockMetadata(int i) {
		return i;
	}

	public int getMetadata(int i) {
		return i;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		switch( itemstack.getItemDamage() ) {
			case Reference.ORE_META.MAGNETITE:
				return "tile.oreMagnetite";

			case Reference.ORE_META.NICKEL:
				return "tile.oreNickel";
		}

		throw new IndexOutOfBoundsException();
	}
}
