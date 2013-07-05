package electrodynamics.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidUtil {

	public static boolean areSameFluid(FluidStack ls1, FluidStack ls2) {
		return ls1.isFluidEqual(ls2);
	}
	
	public static boolean canStoreFluid(IFluidTank tank, FluidStack fluid) {
		if (tank.getFluid() == null || tank.getFluidAmount() == 0) {
			return true;
		} else if (areSameFluid(tank.getFluid(), fluid) && tank.getFluidAmount() + fluid.amount <= tank.getCapacity()) {
			return true;
		} else {
			return false;
		}
	}
	
}
