package electrodynamics.lib.block;

import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityBasicSieve;
import electrodynamics.tileentity.TileEntitySinteringOven;

public enum Machine {

	SINTERING_FURNACE(Strings.MACHINE_SINTERING_FURNACE, "Sintering Furnace", TileEntitySinteringOven.class),
	BASIC_SIEVE(Strings.MACHINE_BASIC_SIEVE, "Basic Sieve", TileEntityBasicSieve.class);
	
	public String unlocalizedName;
	public String localizedName;
	
	public Class<? extends TileEntity> tileEntity;
	
	private Machine(String unlocalizedName, String localizedName, Class<? extends TileEntity> tileEntity) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.tileEntity = tileEntity;
	}
	
}
