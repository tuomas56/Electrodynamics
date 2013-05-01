package nanocircuit.core.client.render;

import java.util.List;

import nanocircuit.core.configuration.ConfigurationSettings;
import nanocircuit.core.item.ItemHandler;
import nanocircuit.core.util.RenderUtil;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

public class RenderThermalOverlay {

	@ForgeSubscribe
	public void onWorldRenderLast(RenderWorldLastEvent event) {
		GL11.glPushMatrix();
		Entity entity = event.context.mc.renderViewEntity;
		EntityPlayer player = (EntityPlayer) entity;
		RenderUtil.translateToWorldCoords(entity, event.partialTicks);

		if (player.inventory.armorInventory[3] != null && player.inventory.armorInventory[3].itemID == ItemHandler.itemTeslaHelm.itemID) {
			renderEntityHighlight(entity, event.partialTicks);
		}
		
		GL11.glPopMatrix();
	}

	@SuppressWarnings("unchecked")
	private void renderEntityHighlight(Entity entity, float partial) {
		GL11.glDisable(GL11.GL_LIGHTING);

		World world = entity.worldObj;
		int range = (int) (ConfigurationSettings.THERMAL_VIEW_RANGE / 2);
		
		List<Entity> nearbyEntities = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(range, range, range));

		for (Entity ent : nearbyEntities) {
			if (ent instanceof EntityLiving) {
				EntityLiving entLiving = (EntityLiving) ent;
				
				GL11.glPushMatrix();
				GL11.glTranslated(entLiving.posX, entLiving.posY, entLiving.posZ);
				
	            GL11.glDepthMask(false);
	            GL11.glDisable(GL11.GL_DEPTH_TEST);
	            GL11.glDisable(GL11.GL_TEXTURE_2D);
				
	            GL11.glColor4f(0.5F, 0, 0, 1);
				RenderManager.instance.getEntityRenderObject(entLiving).doRender(entLiving, 0, 0, 0, 0, partial);
				
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
	            GL11.glDepthMask(true);
				
				GL11.glPopMatrix();
			}
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
	}

}
