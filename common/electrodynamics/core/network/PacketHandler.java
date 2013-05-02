package electrodynamics.core.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import electrodynamics.core.network.packet.PacketED;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		System.out.println("Received packet");
		
		PacketED packetED = PacketTypeHandler.buildPacket(packet.data);
		packetED.execute(manager, player, FMLCommonHandler.instance().getEffectiveSide());
	}

}
