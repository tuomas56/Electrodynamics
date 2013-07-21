package electrodynamics.gas;

import net.minecraftforge.common.ForgeDirection;

public interface IGasTank {

	public GasStack getGas();
	
	public void setGas(GasStack gas);
	
	public int fill(GasStack gas, ForgeDirection side, boolean doFill);
	
	public GasStack drain(int amount, ForgeDirection side, boolean doDrain);
	
}
