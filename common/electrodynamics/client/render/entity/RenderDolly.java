package electrodynamics.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelDolly;
import electrodynamics.entity.EntityDolly;
import electrodynamics.lib.client.Models;

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
		
		if (dolly.hasBlock()) {
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glScaled(3, 3, 3);
			GL11.glTranslated(0, -0.392, 0);
			renderBlock(dolly.worldObj, new ItemStack(dolly.getBlockID(), 1, dolly.getBlockMeta()));
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	public void renderBlock(World world, ItemStack stack) {
		//Incredibly hackish, but better than essentially writing out a copy of the EntityItem renderer
		boolean fancy = Minecraft.getMinecraft().gameSettings.fancyGraphics;
		Minecraft.getMinecraft().gameSettings.fancyGraphics = true;
		
		EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
		entityitem.getEntityItem().stackSize = 1;
		entityitem.hoverStart = 0.0F;
		RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		
		Minecraft.getMinecraft().gameSettings.fancyGraphics = fancy;
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		renderDolly((EntityDolly) par1Entity, par2, par4, par6, par9);
	}
	
}
