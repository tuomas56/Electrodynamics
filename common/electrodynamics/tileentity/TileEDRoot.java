package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
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
	
}
