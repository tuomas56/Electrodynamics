package electrodynamics.item.hammer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.api.tool.ITool;
import electrodynamics.api.tool.ToolType;
import electrodynamics.core.CreativeTabED;

public class ItemHammer extends Item implements ITool {

	public ItemHammer(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabED.tool);
	}
	
	public ItemHammer(int id, int maxDamage) {
		this(id);
		setMaxDamage(maxDamage);
	}

	@Override
	public ToolType getToolType() {
		return ToolType.HAMMER;
	}

	@Override
	public void onToolUsed(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
		stack.damageItem(1, player);		
	}
	
}
