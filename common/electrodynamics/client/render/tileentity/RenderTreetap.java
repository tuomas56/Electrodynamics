package electrodynamics.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelLatexBucket;
import electrodynamics.client.model.ModelTreeTap;
import electrodynamics.client.render.util.LiquidRenderer;
import electrodynamics.lib.client.Models;
import electrodynamics.tileentity.TileEntityTreetap;

public class RenderTreetap extends TileEntitySpecialRenderer {

	private ModelTreeTap modelTreetap;
	private ModelLatexBucket modelBucket;
	
	public RenderTreetap() {
		modelTreetap = new ModelTreeTap();
		modelBucket = new ModelLatexBucket();
	}

	public void renderTreetapAt(TileEntityTreetap tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x + 0.5, y + 0.7, z + 0.69);
		GL11.glRotatef(180, 1, 0, 0);
		
		switch(tile.rotation) {
			case EAST: {
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslated(0.19, 0, 0.17);
				break;
			}
			case NORTH: {
				GL11.glRotatef(-180, 0, 1, 0);
				break;
			}
			case SOUTH: {
				GL11.glTranslated(0, 0, 0.37);
				break;
			}
			case WEST: {
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslated(-0.19, 0, 0.17);
				break;
			}
			default: break;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_TREETAP);
		modelTreetap.render(0.0625F);
		
		if (tile.hasBucket) {
			renderBucket(tile);
			
			LiquidStack liquid = LiquidDictionary.getCanonicalLiquid("Latex");
		
			if (tile.liquidAmount > 0) {
				int[] displayListBucket = LiquidRenderer.getLiquidDisplayLists(liquid, tile.worldObj, false, 0, 0, 0.375F, 0.375F, 100, "latexBucket");
				renderLiquidInBucket(displayListBucket, liquid, tile.liquidAmount, 1000);
			}
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	public void renderBucket(TileEntityTreetap tile) {
		GL11.glPushMatrix();
		GL11.glTranslated(0, -0.84, 0);
		GL11.glRotatef(90, 0, 1, 0);
		
		String texture = (tile.liquidAmount == 1000 ? Models.TEX_BUCKET_FULL : Models.TEX_BUCKET);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		modelBucket.render(0.0625F);
		GL11.glPopMatrix();
	}
	
	public void renderLiquidPouring(int[] displayList, LiquidStack liquid) {
		GL11.glPushMatrix();
		if (displayList != null) {
			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		
			bindTextureByName(LiquidRenderer.getLiquidSheet(liquid));

			GL11.glPushMatrix();
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(15, 0, 0, 1);
			GL11.glTranslated(-0.375, -0.0625, -0.0625);
			GL11.glCallList(displayList[10]);
			GL11.glPopMatrix();
			
			GL11.glPushMatrix();
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslated(-0.05, -0.125, -0.0625);
			GL11.glCallList(displayList[10]);
			GL11.glPopMatrix();
			
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}
	
	public void renderLiquidInBucket(int[] displayList, LiquidStack liquid, int amount, int maxCapacity) {
		GL11.glPushMatrix();
		if (displayList != null) {
			GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			
			bindTextureByName(LiquidRenderer.getLiquidSheet(liquid));
			GL11.glPushMatrix();
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslated(-0.1875, -0.625, -0.1875);
			GL11.glCallList(displayList[Math.round(((float)amount / (float)maxCapacity) * 30)]);
			GL11.glPopMatrix();
			
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		if (((TileEntityTreetap)tile).rotation != null) {
			renderTreetapAt((TileEntityTreetap) tile, x, y, z, partial);
		}
	}
	
	
	
}
