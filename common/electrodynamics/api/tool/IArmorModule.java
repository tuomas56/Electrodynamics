package electrodynamics.api.tool;

import electrodynamics.lib.item.ArmorModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IArmorModule {

	/** Name of module */
	public String getModuleName(ItemStack stack);
	
	/** Description of module */
	public String getModuleDescription(ItemStack stack);
	
	/** Integer array of armor types this module can be assigned to. */
	public int[] validArmorTypes(ItemStack stack);
	
	/** Bridge through to Item method onArmorTick */
	public void onArmorTick(ArmorModule module, World world, EntityPlayer player, ItemStack itemStack);
	
	/** Bridge through to Module logic method onKeypress */
	public void onKeypress(ArmorModule module, EntityPlayer player, ItemStack stack, int key);
	
}
