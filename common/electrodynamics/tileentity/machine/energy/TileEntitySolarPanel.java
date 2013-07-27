package electrodynamics.tileentity.machine.energy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.tileentity.TileEntityEDRoot;

public class TileEntitySolarPanel extends TileEntityEDRoot {

	@SideOnly(Side.CLIENT)
	public float currAngle = 0.0F;
	
	@SideOnly(Side.CLIENT)
	public float setAngle = 0.9F;
	
	@Override
	public void updateEntity() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			if (currAngle < setAngle) {
				currAngle += 0.01F;
			} else if (currAngle > setAngle) {
				currAngle -= 0.01F;
			}
			
			this.setAngle = this.worldObj.provider.calculateCelestialAngle(this.worldObj.getWorldTime(), 0);
		}
	}
	
}
