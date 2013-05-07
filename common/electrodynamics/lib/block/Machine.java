package electrodynamics.lib.block;

import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntitySinteringFurnace;

public enum Machine {

	SINTERING_FURNACE(Strings.MACHINE_SINTERING_FURNACE, "Sintering Furnace", 0, TileEntitySinteringFurnace.class);
	
	public String unlocalizedName;
	public String localizedName;
	
	public int guiID;
	
	public Class<? extends TileEntity> tileEntity;
	
	private Machine(String unlocalizedName, String localizedName, int guiID, Class<? extends TileEntity> tileEntity) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.guiID = guiID;
		this.tileEntity = tileEntity;
	}
	
}
