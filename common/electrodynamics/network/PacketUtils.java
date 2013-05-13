package electrodynamics.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.core.CoreUtils;

public class PacketUtils {

	public static void sendToPlayer(EntityPlayer player, Packet packet) {
		if (packet != null) {
			((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(packet);
		}
	}

	public static void sendToPlayers(Packet packet, World world, double x, double y, double z, int maxDistance) {
		if ((CoreUtils.isServer(world)) && (packet != null)) {
			for (int j = 0; j < world.playerEntities.size(); j++) {
				EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);

				if ((Math.abs(player.posX - x) <= maxDistance) && (Math.abs(player.posY - y) <= maxDistance) && (Math.abs(player.posZ - z) <= maxDistance))
					player.playerNetServerHandler.sendPacketToPlayer(packet);
			}
		}
	}

	public static void sendToPlayers(Packet packet, TileEntity tile) {
		sendToPlayers(packet, tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord, 192);
	}

	public static void sendToServer(Packet packet) {
		if (packet != null) {
			PacketDispatcher.sendPacketToServer(packet);
		}
	}

	public static NBTTagCompound readNBTTagCompound(DataInputStream par0DataInputStream) throws IOException {
		short short1 = par0DataInputStream.readShort();

		if (short1 < 0) {
			return null;
		} else {
			byte[] abyte = new byte[short1];
			par0DataInputStream.readFully(abyte);
			return CompressedStreamTools.decompress(abyte);
		}
	}

	public static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutputStream par1DataOutputStream) throws IOException {
		if (par0NBTTagCompound == null) {
			par1DataOutputStream.writeShort(-1);
		} else {
			byte[] abyte = CompressedStreamTools.compress(par0NBTTagCompound);
			par1DataOutputStream.writeShort((short) abyte.length);
			par1DataOutputStream.write(abyte);
		}
	}
	
}
