package electrodynamics.client.render.entity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelDolly;
import electrodynamics.entity.EntityDolly;
import electrodynamics.lib.client.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class RenderDolly extends Render {

	public ModelDolly modelDolly;
	
	public RenderDolly() {
		this.modelDolly = new ModelDolly();
	}
	
	public void renderDolly(EntityDolly dolly, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslated(x, y, z);

		GL11.glRotatef(180, 1, 0, 0);
		GL11.glTranslated(0, -1.5, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_DOLLY);
		modelDolly.render(0.0625F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderDolly((EntityDolly) par1Entity, par2, par4, par6, par9);
	}
	
}
