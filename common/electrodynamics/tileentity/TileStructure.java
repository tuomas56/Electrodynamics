package electrodynamics.tileentity;


import electrodynamics.mbs.MultiBlockStructure;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileStructure extends TileEntityGeneric {

	// The coordinates of the central TE.
	protected int targetX, targetY, targetZ;

	// Whether if this TE is part of the structure.
	protected boolean isValidStructure = false;

	// The orientation of the MBS as a whole (relative to the MBS's pattern design).
	protected int rotation;

	public void validateStructure(MultiBlockStructure multiBlockStructure, int rotation, int x, int y, int z) {
		this.rotation = rotation;
		this.targetX = x;
		this.targetY = y;
		this.targetZ = z;
		this.isValidStructure = true;
	}

	public void invalidateStructure() {
		isValidStructure = false;
	}

	public boolean isValidStructure() {
		return isValidStructure;
	}

	public boolean isCentralTileEntity() {
		return xCoord == targetX && yCoord == targetY && zCoord == targetZ;
	}

	public TileStructure getCentralTileEntity() {
		if( isValidStructure ) {
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
		isValidStructure = tag.getBoolean( "isPart" );
		targetX = tag.getInteger( "targetX" );
		targetY = tag.getInteger( "targetY" );
		targetZ = tag.getInteger( "targetZ" );
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT( nbt );
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean( "isPart", isValidStructure );
		tag.setInteger( "targetX", targetX );
		tag.setInteger( "targetY", targetY );
		tag.setInteger( "targetZ", targetZ );
		nbt.setTag( "structure", tag );
	}

	public static TileStructure createNewPlaceHolderTE() {
		return new TileStructure() {
			@Override
			public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
				return false; // place holder.
			}
		};
	}

}
