package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.mbs.MBSManager;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.network.PacketTypeHandler;

public class PacketInitializeMBS extends PacketED {

	public int x;
	public int y;
	public int z;
	public int width;
	public int length;
	public int height;
	
	public PacketInitializeMBS(WorldChunk chunk) {
		this(chunk.getBaseCoordinates().x, chunk.getBaseCoordinates().y, chunk.getBaseCoordinates().z, chunk.getWidth(), chunk.getHeight(), chunk.getDepth());
	}
	
	public PacketInitializeMBS(int x, int y, int z, int width, int length, int height) {
		super(PacketTypeHandler.MBS_INIT, false);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.length = length;
		this.height = height;
	}
	
	public PacketInitializeMBS() {
		super(PacketTypeHandler.MBS_INIT, false);
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.width = data.readInt();
		this.length = data.readInt();
		this.height = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
		dos.writeInt(width);
		dos.writeInt(length);
		dos.writeInt(height);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		WorldChunk chunk = new WorldChunk(((EntityPlayer)player).worldObj, x, y, z, width, length, height);
		MBSManager.updateStructuresAt(chunk);
	}

}
