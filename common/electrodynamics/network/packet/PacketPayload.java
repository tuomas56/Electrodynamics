package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.Payload;

public class PacketPayload extends PacketED {

	private int x;
	private int y;
	private int z;
	
	private Payload payload;
	
	public PacketPayload() {
		super(PacketTypeHandler.PAYLOAD, true);
	}

	public PacketPayload(int x, int y, int z, Payload payload) {
		super(PacketTypeHandler.PAYLOAD, true);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.payload = payload;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		
		this.payload = new Payload();
		this.payload.readPayloadData(data);
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
		this.payload.writePayloadData(dos);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		EntityPlayer playerEnt = (EntityPlayer)player;
		TileEntity tile = playerEnt.worldObj.getBlockTileEntity(x, y, z);
		
		if (tile != null) {
			((IPayloadReceiver)tile).handlePayload(payload);
		}
	}
	
}
