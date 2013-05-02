package electrodynamics.control;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBoundClient {

	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key);
	
}
