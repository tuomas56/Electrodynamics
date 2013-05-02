package electrodynamics.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.PacketTypeHandler;

// Note: subclass of PacketED *must* provide a constructor with no parameters.
public abstract class PacketED {

	public final PacketTypeHandler packetType;

	public final boolean isChunkPacket;
	
	public PacketED(PacketTypeHandler type, boolean chunk) {
		this.packetType = type;
		this.isChunkPacket = chunk;
	}
	
	public byte[] writeToByteStream() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			dos.writeByte(packetType.ordinal());
			this.writeData(dos);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return bos.toByteArray();
	}
	
	public void readByteStream(DataInputStream data) {
		try {
			this.readData(data);
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public abstract void readData(DataInputStream data) throws IOException;
	
	public abstract void writeData(DataOutputStream dos) throws IOException;
	
	public abstract void execute(INetworkManager network, Player player, Side side);
	
}
