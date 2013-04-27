package nanocircuit.world;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemStorage extends ItemBlock {
	public ItemStorage(int i) {
		super( i );
		setHasSubtypes( true );
		setUnlocalizedName( "ncmStorage" );
	}

	public int getMetadata(int i) {
		return i;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return StorageBlocks.get( itemstack.getItemDamage() ).getUnlocalizedName();
	}
}
