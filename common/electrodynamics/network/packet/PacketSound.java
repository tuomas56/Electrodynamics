package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.Electrodynamics;
import electrodynamics.network.PacketTypeHandler;

public class PacketSound extends PacketED {

	public String type;
	
	public double x;
	public double y;
	public double z;
	
	public PacketSound() {
		super(PacketTypeHandler.SOUND, false);
	}

	public PacketSound(String type, double x, double y, double z) {
		super(PacketTypeHandler.SOUND, false);
		
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.type = data.readUTF();
		this.x = data.readDouble();
		this.y = data.readDouble();
		this.z = data.readDouble();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeUTF(type);
		dos.writeDouble(this.x);
		dos.writeDouble(this.y);
		dos.writeDouble(this.z);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		Electrodynamics.proxy.handleSoundPacket(type, x, y, z);
	}
	
}
