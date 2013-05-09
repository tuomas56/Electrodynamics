package electrodynamics.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.network.packet.PacketActivate;
import electrodynamics.network.packet.PacketED;
import electrodynamics.network.packet.PacketFX;
import electrodynamics.network.packet.PacketJump;
import electrodynamics.network.packet.PacketKeyPress;
import electrodynamics.network.packet.PacketLightningFX;
import electrodynamics.network.packet.PacketPayload;
import electrodynamics.network.packet.PacketTableUpdate;

public enum PacketTypeHandler {

	KEY(PacketKeyPress.class),
	PAYLOAD(PacketPayload.class),
	TABLE_UPDATE(PacketTableUpdate.class),
	JUMP(PacketJump.class),
	LIGHTNING(PacketLightningFX.class),
	FX(PacketFX.class),
	ACTIVATE(PacketActivate.class);
	
	private Class<? extends PacketED> clazz;
	
	private PacketTypeHandler(Class<? extends PacketED> clazz) {
		this.clazz = clazz;
	}
	
	public static PacketED buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int id = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketED packet = null;
		
		try {
			packet = values()[id].clazz.newInstance();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		packet.readByteStream(dis);
		
		return packet;
	}
	
	public static PacketED buildPacket(PacketTypeHandler type) {
		PacketED packet = null;
		
		try {
			packet = type.clazz.newInstance();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return packet;
	}
	
	public static Packet fillPacket(PacketED packet) {
		byte[] data = packet.writeToByteStream();
		
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = ModInfo.GENERIC_MOD_ID;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packet.isChunkPacket;
		
		return packet250;
	}
	
	/* Extra packet dispatch methods */
	@SuppressWarnings("unchecked")
	public static void sendPacketToAllInDimensionExceptSender(EntityPlayer player, Packet packet, int dimId) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server != null) {
			ArrayList<EntityPlayer> onlinePlayers = (ArrayList<EntityPlayer>) MinecraftServer.getServerConfigurationManager(server).playerEntityList;
			for (int j = 0; j < onlinePlayers.size(); ++j) {
				EntityPlayerMP entityplayermp = (EntityPlayerMP) onlinePlayers.get(j);

				if (entityplayermp.dimension == dimId && !(entityplayermp.getEntityName().equals(player.getEntityName()))) {
					entityplayermp.playerNetServerHandler.sendPacketToPlayer(packet);
				}
			}
		} else {
			FMLLog.fine("Attempt to send packet to all in dimension without a server instance available");
		}
	}
	
}
