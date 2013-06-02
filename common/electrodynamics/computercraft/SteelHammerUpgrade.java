package electrodynamics.computercraft;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import dan200.computer.api.IHostedPeripheral;
import dan200.turtle.api.ITurtleAccess;
import dan200.turtle.api.ITurtleUpgrade;
import dan200.turtle.api.TurtleSide;
import dan200.turtle.api.TurtleUpgradeType;
import dan200.turtle.api.TurtleVerb;
import electrodynamics.item.EDItems;
import electrodynamics.item.hammer.ItemSteelHammer;

public class SteelHammerUpgrade implements ITurtleUpgrade {

	@Override
	public int getUpgradeID() {
		return 300;
	}

	@Override
	public String getAdjective() {
		return "Steel Hammer";
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
		
		return true;
	}

	@Override
	public Icon getIcon(ITurtleAccess turtle, TurtleSide side) {
		return ItemSteelHammer.texture;
	}

}
