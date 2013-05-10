package electrodynamics.client.render.item;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import electrodynamics.client.model.ModelPlasmaRifle;
import electrodynamics.lib.client.Models;

public class RenderItemPlasmaRifle implements IItemRenderer {

	private ModelPlasmaRifle modelPlasmaRifle;
	
	public RenderItemPlasmaRifle() {
		this.modelPlasmaRifle = new ModelPlasmaRifle();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch(type) {
			case EQUIPPED_FIRST_PERSON: {
				renderRifleInHand1stPerson(.2F, 1.7F, 0, 0.0625F);
				break;
			}
			case EQUIPPED: {
				renderRifleInHand3rdPerson(0, 0, 0, 0.0625F);
				break;
			}
			case ENTITY: {
				renderRifleEntity(0, .8F, 0, 0.0625F);
				break;
			}
			case INVENTORY: {
				renderRifleInInventory(0, 0, 0, 0.0625F);
				break;
			}
			
			default: break;
		}
	}
	
	private void renderRifleInHand1stPerson(float x, float y, float z, float scale) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_PLASMA_RIFLE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-35, 0, 1, 0);
		GL11.glRotatef(180, 0, 0, 1);
		GL11.glScaled(1.5D, 1.5D, 1.5D);
		modelPlasmaRifle.render(scale);
		GL11.glPopMatrix();
	}
	
	private void renderRifleInHand3rdPerson(float x, float y, float z, float scale) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_PLASMA_RIFLE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(1.5D, 1.5D, 1.5D);
		GL11.glRotatef(230, 0, 1, 0);
		GL11.glRotatef(245, 1, 0, 0);
		modelPlasmaRifle.render(scale);
		GL11.glPopMatrix();
	}
	
	private void renderRifleEntity(float x, float y, float z, float scale) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_PLASMA_RIFLE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScaled(1.5D, 1.5D, 1.5D);
		modelPlasmaRifle.render(scale);
		GL11.glPopMatrix();
	}
	
	private void renderRifleInInventory(float x, float y, float z, float scale) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_PLASMA_RIFLE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(.7F, .7F, .7F);
		modelPlasmaRifle.render(scale);
		GL11.glPopMatrix();
	}
	
}
