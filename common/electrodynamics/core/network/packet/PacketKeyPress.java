package electrodynamics.core.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.control.IKeyBound;
import electrodynamics.core.network.PacketTypeHandler;

public class PacketKeyPress extends PacketED {

	public String key;
	
	public PacketKeyPress() {
		super(PacketTypeHandler.KEY, false);
	}

	public PacketKeyPress(String key) {
		super(PacketTypeHandler.KEY, false);
		this.key = key;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(key);
	}
	
	@Override
	public void readData(DataInputStream dos) throws IOException {
		this.key = dos.readUTF();
	}
	
	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		if (side == Side.CLIENT) return;
		
		EntityPlayer playerEntity = (EntityPlayer)player;
		
		if (playerEntity.getCurrentEquippedItem() != null && playerEntity.getCurrentEquippedItem().getItem() instanceof IKeyBound) {
			((IKeyBound)playerEntity.getCurrentEquippedItem().getItem()).doKeybindingAction(playerEntity, playerEntity.getCurrentEquippedItem(), key);
		}
	}
	
}
