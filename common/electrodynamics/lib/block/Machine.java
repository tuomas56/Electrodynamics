package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityBasicSieve;
import electrodynamics.tileentity.TileEntitySinteringOven;

public enum Machine {

	SINTERING_FURNACE(Strings.MACHINE_SINTERING_OVEN, TileEntitySinteringOven.class),
	BASIC_SIEVE(Strings.MACHINE_BASIC_SIEVE, TileEntityBasicSieve.class);
	
	public String unlocalizedName;
	
	public Class<? extends TileEntity> tileEntity;
	
	private Machine(String unlocalizedName, Class<? extends TileEntity> tileEntity) {
		this.unlocalizedName = unlocalizedName;
		this.tileEntity = tileEntity;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_MACHINE_ID, 1, this.ordinal());
	}
	
}
