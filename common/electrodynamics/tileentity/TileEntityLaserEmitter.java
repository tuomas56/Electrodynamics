package electrodynamics.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.client.fx.FXBeam;

public class TileEntityLaserEmitter extends TileEntity {

	@SideOnly(Side.CLIENT)
	public FXBeam laser;
	
	/** Length of laser in blocks */
	public static final int LENGTH = 10;
	
	@Override
	public void updateEntity() {
		updateLaser();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateLaser() {
		if (laser == null) {
			ForgeDirection facing = ForgeDirection.getOrientation(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
			
			double endX = (this.xCoord + 0.5) + ((LENGTH + 0.5) * facing.offsetX);
			double endY = (this.yCoord + 0.5) + ((LENGTH + 0.5) * facing.offsetY);
			double endZ = (this.zCoord + 0.5) + ((LENGTH + 0.5) * facing.offsetZ);
			
			laser = new FXBeam(this.worldObj, this, endX, endY, endZ, 40);
			laser.setEndMod(1.0F);
			laser.setPulse(true);
			Minecraft.getMinecraft().effectRenderer.addEffect(laser);
		}
		laser.keepAlive();
	}
	
}
