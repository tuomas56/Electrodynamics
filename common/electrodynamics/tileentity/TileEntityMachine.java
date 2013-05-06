package electrodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityMachine extends TileEntity {
	
	public int rotation;
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setInteger("rotation", rotation);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		rotation = tag.getInteger("rotation");
	}
	
	@Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
		this.readFromNBT(pkt.customParam1);
    }
	
	@Override
    public Packet getDescriptionPacket()
    {
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, comp);
    }

}
