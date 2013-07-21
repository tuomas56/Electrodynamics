package electrodynamics.gas;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public class GasTank implements IGasTank {

	public String storedGas;
	
	public int storedAmount;
	
	public int capacity;
	
	public GasTank(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public GasStack getGas() {
		return new GasStack(storedGas, storedAmount);
	}

	@Override
	public void setGas(GasStack gas) {
		this.storedGas = gas.gas;
		this.storedAmount = gas.amount;
	}

	@Override
	public int fill(GasStack gas, ForgeDirection side, boolean doFill) {
		if (gas.areTypesEqual(storedGas)) {
			if (this.storedAmount + gas.amount <= this.capacity) {
				return 0;
			} else {
				return gas.amount - (this.capacity - this.storedAmount);
			}
		} else {
			return gas.amount;
		}
	}

	@Override
	public GasStack drain(int amount, ForgeDirection side, boolean doDrain) {
		if (this.storedGas != null && this.storedGas.length() > 0) {
			if (this.storedAmount > 0 && this.storedAmount <= amount) {
				return new GasStack(storedGas, amount);
			} else {
				return new GasStack(storedGas, amount - (amount - this.storedAmount));
			}
		} else {
			return null;
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("gasID", this.storedGas);
		nbt.setInteger("gasAmount", this.storedAmount);
		nbt.setInteger("tankCapacity", this.capacity);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		this.storedGas = nbt.getString("gasID");
		this.storedAmount = nbt.getInteger("storedAmount");
		this.capacity = nbt.getInteger("tankCapacity");
	}
	
}
