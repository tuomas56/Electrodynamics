package nanocircuit.core.client.render;

import nanocircuit.core.configuration.ConfigurationSettings;
import nanocircuit.core.util.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

public class RenderThermalOverlay {

	@ForgeSubscribe
	public void onWorldRenderLast(RenderWorldLastEvent event) {
		GL11.glPushMatrix();
		Entity entity = event.context.mc.renderViewEntity;
		RenderUtil.translateToWorldCoords(entity, event.partialTicks);

		renderMobSpawnOverlay(entity);
		GL11.glPopMatrix();
	}

	private void renderMobSpawnOverlay(Entity entity) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);

		World world = entity.worldObj;
		int x1 = (int) entity.posX;
		int y1 = (int) entity.posY;
		int z1 = (int) entity.posZ;
		int range = (int) (ConfigurationSettings.THERMAL_VIEW_RANGE / 2);
		
		for (int x = x1 - range; x <= x1 + range; x++) {
			for (int z = z1 - range; z <= z1 + range; z++) {
				for (int y = y1 - range; y <= y1 + range; y++) {
					if (world.getBlockId(x, y, z) > 0) {
						GL11.glColor4f(Block.lightValue[world.getBlockId(x, y, z)], 1, 1, 0.25F);
						RenderUtil.drawQuadOnFace(x, y, z, 1.002, ForgeDirection.UP);
						RenderUtil.drawQuadOnFace(x, y, z, 0.002, ForgeDirection.DOWN);
					}
				}
			}
		}
	
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
