package electrodynamics.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelTreeTap;
import electrodynamics.lib.client.Models;
import electrodynamics.tileentity.TileEntityTreetap;

public class RenderTreetap extends TileEntitySpecialRenderer {

	private ModelTreeTap modelTreetap;
	
	public RenderTreetap() {
		modelTreetap = new ModelTreeTap();
	}

	public void renderTreetapAt(TileEntityTreetap tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x + 0.5, y + 0.38, z + 0.5);
		GL11.glRotatef(180, 1, 0, 0);
		
		switch(tile.rotation) {
			case EAST: {
				GL11.glRotatef(-90, 0, 1, 0);
				break;
			}
			case NORTH: {
				GL11.glRotatef(-180, 0, 1, 0);
				break;
			}
			case SOUTH: {
				break;
			}
			case WEST: {
				GL11.glRotatef(90, 0, 1, 0);
				break;
			}
			default: break;
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_TREETAP);
		modelTreetap.render(0.0625F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		if (((TileEntityTreetap)tile).rotation != null) {
			renderTreetapAt((TileEntityTreetap) tile, x, y, z, partial);
		}
	}
	
	
	
}
