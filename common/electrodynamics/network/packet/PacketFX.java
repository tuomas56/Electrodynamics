package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.Electrodynamics;
import electrodynamics.lib.client.FXType;
import electrodynamics.network.PacketTypeHandler;

public class PacketFX extends PacketED {

	public FXType type;
	
	public double x;
	public double y;
	public double z;
	
	public int[] extraInfo;
	
	public PacketFX() {
		super(PacketTypeHandler.FX, false);
	}

	public PacketFX(FXType type, double x, double y, double z, int[] extraInfo) {
		super(PacketTypeHandler.FX, false);
		
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
		this.extraInfo = extraInfo;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.type = FXType.values()[data.readByte()];
		this.x = data.readDouble();
		this.y = data.readDouble();
		this.z = data.readDouble();
		this.extraInfo = new int[data.readInt()];
		
		for (int i=0; i<extraInfo.length; i++) {
			extraInfo[i] = data.readInt();
		}
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeByte(this.type.ordinal());
		dos.writeDouble(this.x);
		dos.writeDouble(this.y);
		dos.writeDouble(this.z);
		dos.writeInt(extraInfo.length);
		
		for (int i=0; i<extraInfo.length; i++) {
			dos.writeInt(extraInfo[i]);
		}
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		Electrodynamics.proxy.handleFXPacket(type, x, y, z, extraInfo);
	}
	
}
