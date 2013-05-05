package electrodynamics.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.lib.Models;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSinteringFurnace extends TileEntitySpecialRenderer {

	private ModelSinteringFurnace modelSinteringFurnace;
	
	public RenderSinteringFurnace() {
		this.modelSinteringFurnace = new ModelSinteringFurnace();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180, 0, 0, 1);

		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_SINT_FURNACE);
		modelSinteringFurnace.render(0.0625F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
