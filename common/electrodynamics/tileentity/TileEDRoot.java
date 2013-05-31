package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.CoreUtils;
import electrodynamics.network.PacketUtils;

public class TileEDRoot extends TileEntity {

	public void onBlockActivated(EntityPlayer player) {

	}
	
	public void onBlockAdded() {
		
	}
	
	public void onNeighborUpdate() {
		
	}
	
	public void onBlockBreak() {
		
	}
	
	@Override
	public void updateEntity() {
		if (CoreUtils.isServer(this.worldObj)) {
			if (this.worldObj.getWorldTime() % 4L == 0) {
				sendUpdatePacket(Side.CLIENT);
			}
		}
	}
	
	public void sendUpdatePacket(Side sendTo) {
		if (sendTo.isClient()) {
			PacketUtils.sendToPlayers(getDescriptionPacket(), this);
		} else if (sendTo.isServer()) {
			PacketUtils.sendToServer(getDescriptionPacket());
		}
	}
	
	public NBTTagCompound getStoredNBTData() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return tag;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 3, getStoredNBTData());
	}
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
    }
	
}
