package electrodynamics.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityDolly extends Entity {

	public World world;
	
	public EntityDolly(World world) {
		super(world);
		
		this.world = world;
	}

	public EntityDolly(World world, int x, int y, int z) {
		super(world);
		setPosition(x, y, z);
	}
	
	@Override
	public boolean canTriggerWalking() {
        return false;
    }
	
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1, posY + .5, posZ + 1);
	}

	public AxisAlignedBB getBoundingBox() {
		return AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1, posY + .5, posZ + 1);
	}

	public boolean canBePushed() {
		return true;
	}
	
	@Override
	public void entityInit() {
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		
	}
	
}
