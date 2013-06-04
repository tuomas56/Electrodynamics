package electrodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityBasicKiln extends TileEntityMachine {

	public final float ROTATIONAL_MAX = 1.5F;
	public float doorAngle = 0;

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT( tag );
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT( tag );
	}

}
