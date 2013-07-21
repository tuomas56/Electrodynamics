package electrodynamics.gas;

import net.minecraftforge.common.ForgeDirection;

public interface IGasTankHolder {

	public int fill(GasStack gas, ForgeDirection side, boolean doFill);
	
	public GasStack drain(int amount, ForgeDirection side, boolean doDrain);
	
	public IGasTank[] getTanks(ForgeDirection side);
	
}
