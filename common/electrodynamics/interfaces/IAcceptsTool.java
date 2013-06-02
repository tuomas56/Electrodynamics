package electrodynamics.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.api.tool.ToolType;

public interface IAcceptsTool {

	public boolean accepts(ToolType tool);
	
	public boolean onToolUse(World world, int x, int y, int z, EntityPlayer player, ItemStack stack);
	
}
