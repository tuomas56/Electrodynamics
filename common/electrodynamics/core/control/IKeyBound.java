package electrodynamics.core.control;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {

	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key);
	
}
