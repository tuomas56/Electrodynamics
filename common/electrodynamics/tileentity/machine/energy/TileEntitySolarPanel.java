package electrodynamics.tileentity.machine.energy;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.lib.block.EnergyProduction;
import electrodynamics.tileentity.TileEntityEDRoot;
import electrodynamics.util.BlockUtil;

public class TileEntitySolarPanel extends TileEntityEDRoot {

	@SideOnly(Side.CLIENT)
	private float prevCurrAngle = 0.0F;
	@SideOnly(Side.CLIENT)
	public float currAngle = 0.0F;
	
	private float prevSetAngle = 0.0F;
	public float setAngle;

	public ForgeDirection attached;
	
	@Override
	public void onBlockAdded(ForgeDirection side) {
		this.attached = side;
		//TODO: Reeeender
	}

	@Override
	public void onNeighborUpdate() {
		if (BlockUtil.getBlockOnSide(worldObj, xCoord, yCoord, zCoord, attached) == 0 || this.attached == null) {
			BlockUtil.dropItemFromBlock(worldObj, xCoord, yCoord, zCoord, EnergyProduction.SOLAR_PANEL.toItemStack(), new Random());
			this.invalidate();
			this.worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}
	
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
			if (this.setAngle != 0) {
				this.setAngle = 0;
				sendAngleUpdate();
			}
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
	
	private Vec3 getBlockVector() {
		return Vec3.fakePool.getVecFromPool(xCoord + 0.5, yCoord + 1.5, zCoord + 0.5);
	}
	
	private Vec3 getPanelFaceVector() {
		float yaw = this.setAngle != 0 ? (this.setAngle > 0 && this.setAngle <= 0.45 ? -90 : 0) : (this.setAngle < 0 && this.setAngle >= -0.45 ? 90 : 0);

		float f1 = MathHelper.cos(yaw * 0.017453292F - (float)Math.PI);
		float f2 = MathHelper.sin(yaw * 0.017453292F - (float)Math.PI);
		float f3 = -MathHelper.cos(this.setAngle * 0.017453292F);
		float f4 = MathHelper.sin(this.setAngle * 0.017453292F);
        return this.worldObj.getWorldVec3Pool().getVecFromPool(f2 * f3, f4, f1 * f3);
	}
	
	public Vec3 getPanelFaceCoords() {
		final float RANGE = 10F;
		
		Vec3 base = getBlockVector();
		Vec3 face = getPanelFaceVector();
		Vec3 look = base.addVector(face.xCoord + RANGE, face.yCoord + RANGE, face.zCoord);
	
		return look;
	}
	
	public MovingObjectPosition getPanelFaceBlock() {
		return this.worldObj.rayTraceBlocks_do_do(getBlockVector(), getPanelFaceCoords(), false, true);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setByte("attach", (byte) this.attached.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.attached = ForgeDirection.VALID_DIRECTIONS[nbt.getByte("attach")];
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
