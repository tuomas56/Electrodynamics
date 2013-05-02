package electrodynamics.core.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import electrodynamics.core.lib.ModInfo;
import electrodynamics.core.network.packet.PacketED;
import electrodynamics.core.network.packet.PacketKeyPress;

public enum PacketTypeHandler {

	KEY(PacketKeyPress.class);
	
	private Class<? extends PacketED> clazz;
	
	private PacketTypeHandler(Class<? extends PacketED> clazz) {
		this.clazz = clazz;
	}
	
	public static PacketED buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int id = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketED packet = null;
		
		System.out.println("Received " + values()[id].toString());
		
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
	
}
