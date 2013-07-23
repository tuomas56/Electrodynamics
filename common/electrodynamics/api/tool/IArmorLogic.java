package electrodynamics.api.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IArmorLogic {

	public void onArmorTick(World world, EntityPlayer player, ItemStack stack);
	
	public void onKeypress(EntityPlayer player, ItemStack stack, int key);
	
}
