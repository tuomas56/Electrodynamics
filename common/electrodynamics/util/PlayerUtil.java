package electrodynamics.util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class PlayerUtil {

	public static ForgeDirection determine3DOrientation_F(World world, int x, int y, int z, EntityLiving entity) {
		return (ForgeDirection.getOrientation(determine3DOrientation_I(world, x, y, z, entity)));
	}
	
	public static int determine3DOrientation_I(World world, int x, int y, int z, EntityLiving entity) {
		if (MathHelper.abs((float) entity.posX - (float) x) < 2.0F && MathHelper.abs((float) entity.posZ - (float) z) < 2.0F) {
			double d0 = entity.posY + 1.82D - (double) entity.yOffset;

			if (d0 - (double) y > 2.0D) {
				return 1;
			}

			if ((double) y - d0 > 0.0D) {
				return 0;
			}
		}

		int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
	}
	
	public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, float padding) {
		return getPointedEntity(world, entityplayer, range, padding, false);
	}

	public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, float padding, boolean nonCollide) {
		Entity pointedEntity = null;
		double d = range;
		Vec3 vec3d = Vec3.fakePool.getVecFromPool(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);
		Vec3 vec3d1 = entityplayer.getLookVec();
		Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d);
		float f1 = padding;
		List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.addCoord(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d).expand(f1, f1, f1));

		double d2 = 0.0D;
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (((entity.canBeCollidedWith()) || (nonCollide)) && (world.rayTraceBlocks_do_do(world.getWorldVec3Pool().getVecFromPool(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ), world.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ), false, true) == null)) {
				float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
				AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
				MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);
				if (axisalignedbb.isVecInside(vec3d)) {
					if ((0.0D < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = 0.0D;
					}

				} else if (movingobjectposition != null) {
					double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
					if ((d3 < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = d3;
					}
				}
			}
		}
		return pointedEntity;
	}

	public static Entity getPointedEntity(World world, EntityPlayer entityplayer, double range, Class clazz) {
		Entity pointedEntity = null;
		double d = range;
		Vec3 vec3d = Vec3.fakePool.getVecFromPool(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ);
		Vec3 vec3d1 = entityplayer.getLookVec();
		Vec3 vec3d2 = vec3d.addVector(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d);
		float f1 = 1.1F;
		List list = world.getEntitiesWithinAABBExcludingEntity(entityplayer, entityplayer.boundingBox.addCoord(vec3d1.xCoord * d, vec3d1.yCoord * d, vec3d1.zCoord * d).expand(f1, f1, f1));

		double d2 = 0.0D;
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if ((entity.canBeCollidedWith()) && (world.rayTraceBlocks_do_do(world.getWorldVec3Pool().getVecFromPool(entityplayer.posX, entityplayer.posY + entityplayer.getEyeHeight(), entityplayer.posZ), world.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ), false, true) == null) && (!clazz.isInstance(entity))) {
				float f2 = Math.max(0.8F, entity.getCollisionBorderSize());
				AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f2, f2, f2);
				MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3d, vec3d2);
				if (axisalignedbb.isVecInside(vec3d)) {
					if ((0.0D < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = 0.0D;
					}

				} else if (movingobjectposition != null) {
					double d3 = vec3d.distanceTo(movingobjectposition.hitVec);
					if ((d3 < d2) || (d2 == 0.0D)) {
						pointedEntity = entity;
						d2 = d3;
					}
				}
			}
		}
		return pointedEntity;
	}
	
}
