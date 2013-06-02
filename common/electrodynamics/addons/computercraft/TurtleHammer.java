package electrodynamics.addons.computercraft;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import dan200.computer.api.IHostedPeripheral;
import dan200.turtle.api.ITurtleAccess;
import dan200.turtle.api.ITurtleUpgrade;
import dan200.turtle.api.TurtleSide;
import dan200.turtle.api.TurtleUpgradeType;
import dan200.turtle.api.TurtleVerb;
import electrodynamics.api.tool.ToolType;
import electrodynamics.entity.EntityPlayerFake;
import electrodynamics.interfaces.IAcceptsTool;
import electrodynamics.item.EDItems;
import electrodynamics.item.hammer.ItemSteelHammer;

public class TurtleHammer implements ITurtleUpgrade {

	@Override
	public int getUpgradeID() {
		return 300;
	}

	@Override
	public String getAdjective() {
		return "Hammering";
	}

	@Override
	public TurtleUpgradeType getType() {
		return TurtleUpgradeType.Tool;
	}

	@Override
	public ItemStack getCraftingItem() {
		return new ItemStack(EDItems.itemSteelHammer);
	}

	@Override
	public boolean isSecret() {
		return false;
	}

	@Override
	public IHostedPeripheral createPeripheral(ITurtleAccess turtle, TurtleSide side) {
		return null;
	}

	@Override
	public boolean useTool(ITurtleAccess turtle, TurtleSide side, TurtleVerb verb, int direction) {
		if (verb == TurtleVerb.Dig) {
			World world = turtle.getWorld();
		    Vec3 position = turtle.getPosition();
		    int newX = (int)position.xCoord + net.minecraft.util.Facing.offsetsXForSide[direction];
		    int newY = (int)position.yCoord + net.minecraft.util.Facing.offsetsYForSide[direction];
		    int newZ = (int)position.zCoord + net.minecraft.util.Facing.offsetsZForSide[direction];
		    int blockID = world.getBlockId(newX, newY, newZ);
		    Block block = Block.blocksList[blockID];
		    
		    if (block != null && block instanceof IAcceptsTool) {
		    	IAcceptsTool inter = (IAcceptsTool) block;
		    	
		    	if (inter.accepts(ToolType.HAMMER)) {
		    		inter.onToolUse(world, newX, newY, newZ, new EntityPlayerFake(world), getCraftingItem().copy());
		    		return true;
		    	}
		    }
		}
		
		return false;
	}

	@Override
	public Icon getIcon(ITurtleAccess turtle, TurtleSide side) {
		return ItemSteelHammer.texture;
	}

}
