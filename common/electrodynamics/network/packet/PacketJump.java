package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.PacketTypeHandler;

public class PacketJump extends PacketED {

	private long chargeTime;
	
	public PacketJump() {
		super(PacketTypeHandler.JUMP, false);
	}

	public PacketJump(long chargeTime) {
		super(PacketTypeHandler.JUMP, false);
		
		this.chargeTime = chargeTime;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		chargeTime = data.readLong();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeLong(chargeTime);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		//TODO Calculate jump based on charge time
		((EntityPlayer)player).motionY += chargeTime;
	}
	
}
