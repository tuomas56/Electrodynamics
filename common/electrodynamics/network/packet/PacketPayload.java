package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketTypeHandler;

public class PacketPayload extends PacketED{

	private int x;
	private int y;
	private int z;
	
	private byte[] data;
	
	public PacketPayload() {
		super(PacketTypeHandler.PAYLOAD, true);
	}

	public PacketPayload(int x, int y, int z, byte[] data) {
		super(PacketTypeHandler.PAYLOAD, true);
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.data = data;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		int length = data.readInt();
		
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		
		this.data = new byte[length];
		
		for (int i=0; i<length; i++) {
			this.data[i] = data.readByte();
		}
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(data.length);
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
		
		for (int i=0; i<data.length; i++) {
			dos.writeByte(data[i]);
		}
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		EntityPlayer playerEnt = (EntityPlayer)player;
		((IPayloadReceiver)playerEnt.worldObj.getBlockTileEntity(x, y, z)).handlePayload(data);
		playerEnt.worldObj.markBlockForUpdate(x, y, z);
	}
	
}
