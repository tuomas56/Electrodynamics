package electrodynamics.block.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import electrodynamics.lib.block.EnergyProduction;
import electrodynamics.lib.block.Machine;

public class ItemBlockEnergy extends ItemBlock {

	public ItemBlockEnergy(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnergyProduction.values()[stack.getItemDamage()].unlocalizedName;
	}
	
}
