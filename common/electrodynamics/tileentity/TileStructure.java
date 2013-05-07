package electrodynamics.tileentity;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class TileStructure extends TileEntity {

	// The coordinates of the central TE.
	protected int targetX, targetY, targetZ;

	// Whether if this TE is part of the structure.
	protected boolean isPartOfStructure = false;

	public void validateStructure(int rotation, int x, int y, int z) {
		this.targetX = x;
		this.targetY = y;
		this.targetZ = z;
		this.isPartOfStructure = true;
	}

	public void invalidateStructure() {
		isPartOfStructure = false;
	}

	public boolean isCentralTileEntity() {
		return xCoord == targetX && yCoord == targetY && zCoord == targetZ;
	}

	public TileStructure getCentralTileEntity() {
		if( isPartOfStructure ) {
			if( this.isCentralTileEntity() ) {
				return this;
			}
			return (TileStructure) worldObj.getBlockTileEntity( targetX, targetY, targetZ );
		}
		return null;
	}

	public abstract boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff);

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT( nbt );
		NBTTagCompound tag = nbt.getCompoundTag( "structure" );
		isPartOfStructure = tag.getBoolean( "isPart" );
		targetX = tag.getInteger( "targetX" );
		targetY = tag.getInteger( "targetY" );
		targetZ = tag.getInteger( "targetZ" );
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT( nbt );
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean( "isPart", isPartOfStructure );
		tag.setInteger( "targetX", targetX );
		tag.setInteger( "targetY", targetY );
		tag.setInteger( "targetZ", targetZ );
		nbt.setTag( "structure", tag );
	}

}
