package electrodynamics.block.item;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.block.StructureComponent;


public class ItemBlockStructure extends ItemBlockGeneric {

	public ItemBlockStructure(int itemID) {
		super( itemID );
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int metadata) {
		return metadata;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return StructureComponent.values()[itemstack.getItemDamage()].getUnlocalizedName();
	}
	
}
