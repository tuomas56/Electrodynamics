package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TileEntitySinteringOven extends TileEntityMachine {

	public final int ROTATIONAL_MAX = 2;
	
	public float doorAngle = 0;
	
	public boolean open = false;
	
	/** Based off of furnace fuel burn time, but constantly drained "to keep oven hot" */
	public int fuelLevel;
	
	@Override
	public void updateEntity() {
		if (open && doorAngle <= ROTATIONAL_MAX) {
			doorAngle += 0.2F;
		} else if (!open && doorAngle > 0) {
			doorAngle -= 0.2F;
		}
		
		if (doorAngle < 0) {
			doorAngle = 0;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("open", open);
		nbt.setInteger("fuelLevel", fuelLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.open = nbt.getBoolean("open");
		this.fuelLevel = nbt.getInteger("fuelLevel");
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		open = !open;
		dirty = true;
		return false;
	}

}
