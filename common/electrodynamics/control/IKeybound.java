package electrodynamics.control;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;

public interface IKeybound {

	public Side getSide();
	
	public Type getType();
	
	public void onKeypress(EntityPlayer player, ItemStack stack, int key);
	
	public enum Type {
		HELD, WORN, BOTH;
	}
	
}
