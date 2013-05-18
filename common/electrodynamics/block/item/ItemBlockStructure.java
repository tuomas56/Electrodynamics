package electrodynamics.block.item;

import electrodynamics.lib.block.StructureComponent;
import net.minecraft.item.ItemStack;


public class ItemBlockStructure extends ItemBlockGeneric {

	public ItemBlockStructure(int itemID) {
		super( itemID );
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return StructureComponent.values()[itemstack.getItemDamage()].getUnlocalizedName();
	}

}
