package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.control.IKeybound;
import electrodynamics.control.IKeybound.Type;
import electrodynamics.network.PacketTypeHandler;

public class PacketKeyPress extends PacketED {

	public int key;
	
	public Type type;
	
	public PacketKeyPress() {
		super(PacketTypeHandler.KEY, false);
	}

	public PacketKeyPress(int key, Type type) {
		super(PacketTypeHandler.KEY, false);
		this.key = key;
		this.type = type;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(key);
		data.writeByte(type.ordinal());
	}
	
	@Override
	public void readData(DataInputStream dos) throws IOException {
		this.key = dos.readInt();
		this.type = Type.values()[dos.readByte()];
	}
	
	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		if (side == Side.CLIENT) return;
		
		EntityPlayer playerEntity = (EntityPlayer)player;
		
		if (playerEntity.getCurrentEquippedItem() != null) {
			ItemStack current = playerEntity.getCurrentEquippedItem();
			
			if (current.getItem() instanceof IKeybound) {
				Type type = ((IKeybound)current.getItem()).getType();
				
				if (type == this.type || type == Type.BOTH) {
					((IKeybound)current.getItem()).onKeypress(playerEntity, current, key);
				}
			}
		}
		
		for (ItemStack armor : playerEntity.inventory.armorInventory) {
			if (armor != null) {
				if (armor.getItem() instanceof IKeybound) {
					Type type = ((IKeybound)armor.getItem()).getType();
					
					if (type == this.type || type == Type.BOTH) {
						((IKeybound)armor.getItem()).onKeypress(playerEntity, armor, key);
					}
				}
			}
		}
	}
	
}
