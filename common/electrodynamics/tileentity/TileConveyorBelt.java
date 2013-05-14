package electrodynamics.tileentity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class TileConveyorBelt extends TileStructure {
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

	@Override
	public void updateEntity() {
		// todo: move the entities on the right direction.

//		List<Entity> entities = getEntitiesAbove( EntityItem.class );
//		for(Entity entity : entities) {
//			ItemStack itemStack = ((EntityItem) entity).getEntityItem();
//
//		}

	}

	@SuppressWarnings("unchecked")
	private List<Entity> getEntitiesAbove(Class<? extends Entity> entity) {
		AxisAlignedBB aabb = AxisAlignedBB.getAABBPool().getAABB( xCoord, yCoord + 1, zCoord, xCoord + 1, yCoord + 2, zCoord + 1 );
		if( entity == null )
			entity = Entity.class;
		return worldObj.getEntitiesWithinAABB( entity, aabb );
	}


}
