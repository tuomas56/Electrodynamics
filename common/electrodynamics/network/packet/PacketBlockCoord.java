package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.PacketTypeHandler;

public class PacketBlockCoord extends PacketED {

	public int x;
	public int y;
	public int z;
	
	public PacketBlockCoord() {
		super(PacketTypeHandler.TABLE_CLEAR, false);
	}
	
	public PacketBlockCoord(int x, int y, int z) {
		super(PacketTypeHandler.TABLE_CLEAR, false);
		
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {}

}
