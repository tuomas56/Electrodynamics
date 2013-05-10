package electrodynamics.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.api.tool.ITool;
import electrodynamics.api.tool.ToolType;
import electrodynamics.core.CreativeTabED;

public class ItemHandheldSieve extends Item implements ITool {
 
	public ItemHandheldSieve(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public ToolType getToolType() {
		return ToolType.SIEVE;
	}

	@Override
	public void onToolUsed(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
		stack.damageItem(1, player);
	}
	
}
