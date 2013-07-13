package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.interfaces.IRedstoneUser;
import electrodynamics.network.PacketTypeHandler;

public class PacketUpdateRedstone extends PacketED {

	public int x;
	public int y;
	public int z;
	
	public int strength;
	
	public PacketUpdateRedstone() {
		super(PacketTypeHandler.REDSTONE_STRENGTH, false);
	}

	public PacketUpdateRedstone(int x, int y, int z, int strength) {
		super(PacketTypeHandler.REDSTONE_STRENGTH, false);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.strength = strength;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.strength = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(this.x);
		dos.writeInt(this.y);
		dos.writeInt(this.z);
		dos.writeInt(this.strength);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		TileEntity tile = ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
		
		if (tile != null && tile instanceof IRedstoneUser) {
			((IRedstoneUser)tile).updateSignalStrength(strength);
		}
	}
	
}
