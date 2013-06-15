package electrodynamics.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityRSSource extends TileEntity {

	// Not much use at the moment, but likely for more configurable RS source from emitter
	// Currently just used to prevent lingering source blocks
	
	public int timeUntilDeath;
	
	@Override
	public void updateEntity() {
		if (timeUntilDeath > 0) {
			--timeUntilDeath;
		} else {
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}
	
	public void keepAlive() {
		// Run every tick as long as the emitter is running, with breathing room for potential sync issues
		this.timeUntilDeath = 20;
	}
	
}
