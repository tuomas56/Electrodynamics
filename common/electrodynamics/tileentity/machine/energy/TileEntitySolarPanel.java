package electrodynamics.tileentity.machine.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.tileentity.TileEntityEDRoot;

public class TileEntitySolarPanel extends TileEntityEDRoot {

	@SideOnly(Side.CLIENT)
	private float prevCurrAngle = 0.0F;
	@SideOnly(Side.CLIENT)
	public float currAngle = 0.0F;
	
	private float prevSetAngle = 0.0F;
	public float setAngle;

	@Override
	public void updateEntityClient() {
		if (currAngle < setAngle) {
			currAngle += 0.01;
			currAngle = (float) (Math.round(currAngle * 100.0) / 100.0);
		} else if (currAngle > setAngle) {
			currAngle -= 0.01;
			currAngle = (float) (Math.round(currAngle * 100.0) / 100.0);
		}
	}
	
	@Override
	public void updateEntityServer() {
		this.setAngle = -(float) (Math.round(calculateAngle() * 100.0) / 100.0);
		
		if (this.setAngle > 0.45 || this.setAngle < -0.45 || !this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {
			this.setAngle = 0;
		}
		
		if (this.setAngle != this.prevSetAngle) {
			sendAngleUpdate();
		}
	}
	
	private float calculateAngle() {
		final float TIME_TO_ANGLE = 66.666F;
		long worldTime = getWorldTime();
		
		float celestialAngle = ((worldTime / TIME_TO_ANGLE) / 100);
		return celestialAngle - 0.9F;
	}
	
	private long getWorldTime() {
		final long TOTAL_WORLD_TIME = 24000;
		
		long worldTime = this.worldObj.getWorldTime();
		if (worldTime > TOTAL_WORLD_TIME) {
			long remainder = worldTime % TOTAL_WORLD_TIME;
			
			if (remainder != 0) {
				return remainder;
			} else {
				return worldTime - (TOTAL_WORLD_TIME * (worldTime / TOTAL_WORLD_TIME));
			}
		}
		return worldTime;
	}
	
	@Override
	public void onDescriptionPacket(NBTTagCompound nbt) {
		super.onDescriptionPacket(nbt);

		this.prevSetAngle = setAngle;
		this.setAngle = nbt.getFloat("setAngle");
	}
	
	@Override
	public void getDescriptionForClient(NBTTagCompound nbt) {
		super.getDescriptionForClient(nbt);
		
		nbt.setFloat("setAngle", this.setAngle);
	}
	
	@Override
	public void onUpdatePacket(NBTTagCompound nbt) {
		super.onUpdatePacket(nbt);
		
		if (nbt.hasKey("setAngle")) {
			this.prevSetAngle = setAngle;
			this.setAngle = nbt.getFloat("setAngle");
		}
	}
	
	private void sendAngleUpdate() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("setAngle", this.setAngle);
		sendUpdatePacket(nbt);
	}
	
}
