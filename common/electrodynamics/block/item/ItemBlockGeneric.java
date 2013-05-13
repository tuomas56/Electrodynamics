package electrodynamics.block.item;


import electrodynamics.block.BlockGeneric;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGeneric extends ItemBlock {

	public ItemBlockGeneric(int itemID) {
		super( itemID );
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		Block block = Block.blocksList[itemStack.itemID];
		if( block != null && block instanceof BlockGeneric ) {
			((BlockGeneric) block).getSubBlocksArray()[itemStack.getItemDamage()].getUnlocalizedName();
		}
		return null;
	}


}
