package electrodynamics.tileentity.structure;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityValve extends TileEntityStructure implements IFluidHandler {

	/* IFLUIDHANDLER */
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return getCentralTank().fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return getCentralTank().drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getCentralTank().drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return getCentralTank().canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return getCentralTank().canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return getCentralTank().getTankInfo(from);
	}
	
	private IFluidHandler getCentralTank() {
		if (this.isValidStructure()) {
			TileEntityStructure central = this.getCentralTileEntity();
			
			if (central instanceof IFluidHandler) {
				return ((IFluidHandler)central);
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
