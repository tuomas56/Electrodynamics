package electrodynamics.tileentity.structure;

import java.util.List;


import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityConveyorBelt extends TileEntityStructure {
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

	@Override
	public void updateEntity() {
		List<Entity> entities = getEntitiesAbove( EntityItem.class );
		for(Entity entity : entities) {
			ForgeDirection dir = ForgeDirection.getOrientation(this.rotation);
			
			entity.motionX += (0.1 * dir.offsetX);
			entity.motionZ += (0.1 * dir.offsetZ);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Entity> getEntitiesAbove(Class<? extends Entity> entity) {
		AxisAlignedBB aabb = AxisAlignedBB.getAABBPool().getAABB( xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1 );
		if( entity == null )
			entity = Entity.class;
		return worldObj.getEntitiesWithinAABB( entity, aabb );
	}

}
