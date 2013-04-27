package nanocircuit.world.block.item;

import nanocircuit.world.lib.StorageBlock;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStorage extends ItemBlock {
	public ItemBlockStorage(int i) {
		super( i );
		setHasSubtypes( true );
		setUnlocalizedName( "ncmStorage" );
	}

	public int getMetadata(int i) {
		return i;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return StorageBlock.get( itemstack.getItemDamage() ).getUnlocalizedName();
	}
}
