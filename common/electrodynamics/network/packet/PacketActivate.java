package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.interfaces.IHandleActivationPacket;
import electrodynamics.network.PacketTypeHandler;

/**
 * Generic block activation packet. Used to notify nearby players when a block is activated and client-side events should happen. <br /> Implementation is left to the implementor of IHandleActivationPacket
 * @author Dylan
 *
 */

public class PacketActivate extends PacketED {

	public int x;
	public int y;
	public int z;
	
	public PacketActivate() {
		super(PacketTypeHandler.ACTIVATE, true);
	}

	public PacketActivate(int x, int y, int z) {
		super(PacketTypeHandler.ACTIVATE, true);
		
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
	public void execute(INetworkManager network, Player player, Side side) {
		if (side == Side.SERVER) {
			PacketTypeHandler.sendPacketToAllInDimensionExceptSender((EntityPlayer)player, PacketTypeHandler.fillPacket(new PacketActivate(x, y, z)), ((EntityPlayer)player).worldObj.provider.dimensionId);
		} else {
			TileEntity tile = ((EntityPlayer)player).worldObj.getBlockTileEntity(this.x, this.y, this.z);
			
			if (tile != null && tile instanceof IHandleActivationPacket) {
				((IHandleActivationPacket)tile).activate();
			}
		}
	}

}
