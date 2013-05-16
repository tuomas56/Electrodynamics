package electrodynamics.tileentity;


import electrodynamics.block.BlockGeneric;
import electrodynamics.block.SubBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
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
		subBlock = nbt.getInteger( "subBlock" );
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT( nbt );
		nbt.setInteger( "subBlock", subBlock );
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT( nbttagcompound );
		return new Packet132TileEntityData( this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound );
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT( pkt.customParam1 );
		worldObj.markBlockForUpdate( xCoord, yCoord, zCoord ); // re-render.
	}

	public static final TileEntityGeneric createReplaceableTE() {
		return new TileEntityGeneric() { // Needed to have the client synchronized.

			@Override
			public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
				// Read from NBT
				readFromNBT( pkt.customParam1 );

				// Replace this tile entity with the correct one.
				int subBlock = getSubBlock();
				TileEntityGeneric tileEntity = createReplacementTileEntity( subBlock );
				tileEntity.readFromNBT( pkt.customParam1 );
				worldObj.setBlockTileEntity( xCoord, yCoord, zCoord, tileEntity );
				worldObj.markBlockForRenderUpdate( xCoord, yCoord, zCoord );
			}

			private TileEntityGeneric createReplacementTileEntity(int subBlock) {
				BlockGeneric block = (BlockGeneric) this.getBlockType();
				SubBlock sub = block.getSubBlocksArray()[subBlock];

				return sub.createNewTileEntity( worldObj );
			}
		};
	}


}
