package electrodynamics.item.tesla.logic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class TeslaLogic {

	public abstract void onArmorTick(World world, EntityPlayer player, ItemStack itemStack);
	
}
