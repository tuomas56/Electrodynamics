package electrodynamics.tileentity.structure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

public class TileEntityValve extends TileEntityStructure implements ITankContainer {

	/* ITANKCONTAINER */
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return fill(0, resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return getCentralTank().fill(resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return drain(0, maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return getCentralTank().drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] {getCentralTank()};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return getTanks(direction)[0];
	}

	private ILiquidTank getCentralTank() {
		if (this.isValidStructure()) {
			TileEntityStructure central = this.getCentralTileEntity();
			
			if (central instanceof ITankContainer) {
				return ((ITankContainer)central).getTanks(ForgeDirection.UNKNOWN)[0];
			}
		}
		
		return null;
	}
	
	/* TILEENTITYSTRUCTURE */
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

}
