package electrodynamics.api.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IHammer {

	/**
	 * Called when the hammer is used in the world
	 * @param world Instance of world used in
	 * @param x X-Coordinate of the block that was hammered
	 * @param y Y-Coordinate of the block that was hammered
	 * @param z Z-Coordinate of the block that was hammered
	 * @param stack ItemStack instance of the hammer item
 	 * @param player Player who did the hammering
	 */
	public void onHammerUse(World world, int x, int y, int z, ItemStack stack, EntityPlayer player);
	
}
