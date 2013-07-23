package electrodynamics.item.elmag.logic;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.api.tool.IArmorLogic;
import electrodynamics.core.PlayerTicker;
import electrodynamics.core.PlayerTicker.PlayerData;

public class LogicBacktrack implements IArmorLogic {

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {}

	@Override
	public void onKeypress(EntityPlayer player, ItemStack stack, int key) {
		System.out.println("KEYBORESS");
		if (key == Keyboard.KEY_B) { //TODO Config
			if (PlayerTicker.playerData.containsKey(player.username)) {
				PlayerData.writeToPlayer(player, PlayerTicker.playerData.get(player.username));
			}
		}
	}

}
