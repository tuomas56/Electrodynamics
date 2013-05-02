package electrodynamics.core.control;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.core.lib.ModInfo;
import electrodynamics.core.network.PacketTypeHandler;
import electrodynamics.core.network.packet.PacketKeyPress;

public class KeybindingHandler extends KeyBindingRegistry.KeyHandler {

	public KeybindingHandler() {
		super(KeyBindingHelper.getBindings(), KeyBindingHelper.getIsRepeating());
	}
	
	@Override
	public String getLabel() {
		return ModInfo.GENERIC_MOD_ID + ": " + this.getClass().getSimpleName();
	}
	
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding key, boolean tickEnd, boolean isRepeat) {
		if (tickEnd) {
			if (FMLClientHandler.instance().getClient().inGameHasFocus) {
				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
				
				if (player != null) {
					ItemStack currentItem = player.getCurrentEquippedItem();
					
					if (currentItem != null) {
						if (currentItem.getItem() instanceof IKeyBoundServer) {
							PacketDispatcher.sendPacketToServer(PacketTypeHandler.fillPacket(new PacketKeyPress(key.keyDescription)));
						} else if (currentItem.getItem() instanceof IKeyBoundClient) {
							((IKeyBoundClient)currentItem.getItem()).doKeybindingAction(player, currentItem, key.keyDescription);
						}
					}

					for (ItemStack armor : player.inventory.armorInventory) {
						if (armor != null) {
							if (armor.getItem() instanceof IKeyBoundServer) {
								PacketDispatcher.sendPacketToServer(PacketTypeHandler.fillPacket(new PacketKeyPress(key.keyDescription)));
							} else if (armor.getItem() instanceof IKeyBoundClient) {
								((IKeyBoundClient)armor.getItem()).doKeybindingAction(player, armor, key.keyDescription);
							}
						}
					}
					
				}
			}
		}
	}
	
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
	}
	
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
