package electrodynamics.inventory;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTagable {

	public void writeToNBT(NBTTagCompound tag);
	
	public void readFromNBT(NBTTagCompound tag);
	
}
