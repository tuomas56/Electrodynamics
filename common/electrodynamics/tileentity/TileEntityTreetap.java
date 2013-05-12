package electrodynamics.tileentity;

import electrodynamics.lib.block.BlockIDs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityTreetap extends TileEntity {

	/** Side the treetap is attached to */
	public ForgeDirection rotation;
	
	@Override
	public void updateEntity() {
	}
	
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
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, comp);
	}
	
	public boolean isSupported() {
		int x = xCoord + rotation.getOpposite().offsetX;
		int y = yCoord;
		int z = zCoord + rotation.getOpposite().offsetZ;
		
		return (this.worldObj.getBlockId(x, y, z) == BlockIDs.BLOCK_RUBBER_WOOD_ID);
	}
	
	public int getAttachedTreeHeight() {
		if (rotation != null) {
			boolean foundEnd = false;
			int height = 0;
			
			int x = xCoord + rotation.getOpposite().offsetX;
			int y = yCoord;
			int z = zCoord + rotation.getOpposite().offsetZ;
			
			while (!foundEnd) {
				if (this.worldObj.getBlockId(x, y, z) != BlockIDs.BLOCK_RUBBER_WOOD_ID) {
					foundEnd = true;
				} else {
					height += 1;
					y += 1;
				}
			}
			
			return height;
		}
		
		return 0;
	}
	
}
