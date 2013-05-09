package electrodynamics.item.hammer;

import electrodynamics.api.tool.IHammer;
import electrodynamics.core.CreativeTabED;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHammer extends Item implements IHammer {

	public ItemHammer(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabED.item);
	}
	
	public ItemHammer(int id, int maxDamage) {
		this(id);
		setMaxDamage(maxDamage);
	}

	@Override
	public void onHammerUse(World world, int x, int y, int z, ItemStack stack, EntityPlayer player) {
		stack.damageItem(1, player);
	}
	
}
