package electrodynamics.tileentity;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class TileEntityGeneric extends TileEntity {

	private int subBlock = -1;

	public void setSubBlock(int subBlock) {
		this.subBlock = subBlock;
	}

	public int getSubBlock() {
		return subBlock;
	}

	@Override
	public boolean shouldRefresh(int oldID, int newID, int oldMeta, int newMeta, World world, int x, int y, int z) {
		return oldID != newID; // only refresh if changing IDs.
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT( nbt );
		nbt.setInteger( "subBlock", subBlock );
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT( nbt );
		subBlock = nbt.getInteger( "subBlock" );
	}

}
