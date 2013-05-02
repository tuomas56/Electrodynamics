package electrodynamics.control;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBoundServer {

	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key);
	
}
