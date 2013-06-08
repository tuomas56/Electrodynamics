package electrodynamics.item.elmag.handler;

import java.util.List;

import electrodynamics.client.render.util.RenderUtil;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

public class ThermalOverlayHandler {

	@ForgeSubscribe
	public void onWorldRenderLast(RenderWorldLastEvent event) {
		GL11.glPushMatrix();
		Entity entity = event.context.mc.renderViewEntity;
		EntityPlayer player = (EntityPlayer) entity;
		RenderUtil.translateToWorldCoords(entity, event.partialTicks);
		
		renderEntityHighlight(entity, event.partialTicks);
		
		GL11.glPopMatrix();
	}

	@SuppressWarnings("unchecked")
	private void renderEntityHighlight(Entity entity, float partial) {
		GL11.glDisable(GL11.GL_LIGHTING);

		World world = entity.worldObj;
		int range = (int) (24 / 2);
		
		List<Entity> nearbyEntities = world.getEntitiesWithinAABBExcludingEntity(entity, entity.boundingBox.expand(range, range, range));

		for (Entity ent : nearbyEntities) {
			if (ent instanceof EntityLiving) {
				EntityLiving entLiving = (EntityLiving) ent;
				
				GL11.glPushMatrix();
				GL11.glTranslated(entLiving.posX, entLiving.posY, entLiving.posZ);
				
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glLineWidth(2.0F);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_CULL_FACE);
				
	            GL11.glDepthMask(false);
	            GL11.glDisable(GL11.GL_DEPTH_TEST);
				
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		        GL11.glEnable(GL11.GL_POLYGON_OFFSET_LINE);
		        GL11.glPolygonOffset(-1.0F, -1.0F);
		      
	            GL11.glColor4f(0.5F, 0, 0, 0.2F);
		        RenderManager.instance.getEntityRenderObject(entLiving).doRender(entLiving, 0, 0, 0, 0, partial);
		        
		        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		        GL11.glColor4f(0.5F, 0, 0, 1);
				RenderManager.instance.getEntityRenderObject(entLiving).doRender(entLiving, 0, 0, 0, 0, partial);
			    
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			    GL11.glEnable(GL11.GL_CULL_FACE);
			    GL11.glEnable(GL11.GL_TEXTURE_2D);
			    GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
	            GL11.glDepthMask(true);
				
				GL11.glPopMatrix();
			}
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
	}

}

