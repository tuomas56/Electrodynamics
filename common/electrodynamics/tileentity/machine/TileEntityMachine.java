package electrodynamics.tileentity.machine;

import electrodynamics.tileentity.TileEntityEDRoot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityMachine extends TileEntityEDRoot {

	public ForgeDirection rotation = null;

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		tag.setByte("direction", (byte) rotation.ordinal());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		rotation = ForgeDirection.VALID_DIRECTIONS[tag.getByte("direction")];
	}

	@Override
	public void getDescriptionForClient(NBTTagCompound nbt) {	
		nbt.setByte("direction", (byte) rotation.ordinal());
	}
	
	public void onUpdatePacket(NBTTagCompound nbt) {
		if(nbt.hasKey("direction"))
			rotation = ForgeDirection.VALID_DIRECTIONS[nbt.getByte("direction")];
	}
	
	public void onDescriptionPacket(NBTTagCompound nbt) {
		rotation = ForgeDirection.VALID_DIRECTIONS[nbt.getByte("direction")];
	}

}
