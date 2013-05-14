package electrodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;

public class TileEntityEggTimer extends TileEntityMachine {

	/** Whether or not timer is active */
	public boolean active;
	
	/** Time remaining on the timer */
	public int timeRemaining;
	
	/** Time the timer is set to, used for resetting/calculation/render */
	public int totalTime;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (this.active) {
			if (this.timeRemaining > 0) {
				--timeRemaining;
			} else {
				// Ring
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("active", active);
		nbt.setInteger("timeRemaining", timeRemaining);
		nbt.setInteger("totalTime", totalTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.active = nbt.getBoolean("active");
		this.timeRemaining = nbt.getInteger("timeRemaining");
		this.totalTime = nbt.getInteger("totalTime");
	}
	
}
