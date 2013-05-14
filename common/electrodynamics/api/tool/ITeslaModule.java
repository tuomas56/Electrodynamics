package electrodynamics.api.tool;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ITeslaModule {

	/** Name of module */
	public String getModuleName(ItemStack stack);
	
	/** Description of module */
	public String getModuleDescription(ItemStack stack);
	
	/** Integer array of armor types this module can be assigned to. */
	public int[] validArmorTypes(ItemStack stack);
	
	/** Bridge through to Item method onArmorTick */
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack);
	
}
