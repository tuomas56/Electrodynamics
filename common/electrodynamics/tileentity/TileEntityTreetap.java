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
		System.out.println(getAttachedTreeHeight());
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
	
	private int getAttachedTreeHeight() {
		if (rotation != null) {
			boolean foundEnd = false;
			int height = 1;
			
			int y = yCoord;
			
			while (!foundEnd) {
				if (this.worldObj.getBlockId(xCoord + rotation.offsetX, y, zCoord + rotation.offsetZ) != BlockIDs.BLOCK_RUBBER_WOOD_ID) {
					foundEnd = true;
				}
				
				y += 1;
			}
			
			return height;
		}
		
		return 0;
	}
	
}
