package electrodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.Payload;
import electrodynamics.network.packet.PacketPayload;

public class TileEntityEggTimer extends TileEntityMachine implements IPayloadReceiver {

	/** Whether or not timer is active */
	public boolean active;
	
	/** Time remaining on the timer */
	public int timeRemaining;
	
	/** Time the timer is set to, used for resetting/calculation/render */
	public int totalTime;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (this.active) {
			if (this.timeRemaining > 0) {
				--timeRemaining;
			} else {
				// Ring
			}
		}
	}
	
	@Override
	public void handlePayload(Payload payload) {
		this.active = payload.boolPayload[1];
		this.rotation = ForgeDirection.getOrientation(payload.bytePayload[0]);
		this.timeRemaining = payload.intPayload[0];
		this.totalTime = payload.intPayload[1];
	}
	
	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(1, 1, 2, 0, 0, 0);
		payload.boolPayload[0] = this.active;
		payload.bytePayload[0] = (byte) this.rotation.ordinal();
		payload.intPayload[0] = this.timeRemaining;
		payload.intPayload[1] = this.totalTime;
		PacketPayload packet = new PacketPayload(xCoord, yCoord, zCoord, payload);
		return packet.makePacket();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("active", active);
		nbt.setInteger("timeRemaining", timeRemaining);
		nbt.setInteger("totalTime", totalTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.active = nbt.getBoolean("active");
		this.timeRemaining = nbt.getInteger("timeRemaining");
		this.totalTime = nbt.getInteger("totalTime");
	}
	
}
