package electrodynamics.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.render.util.RenderUtil;
import electrodynamics.tileentity.machine.utilty.TileEntityBlockDeployer;

public class RenderBlockDeployer extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		TileEntityBlockDeployer tileBD = (TileEntityBlockDeployer) tile;
		
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(x + .5, y + .25, z + .5);
		
		GL11.glScaled(2, 2, 2);
		
		if (tileBD.getStackInSlot(0) != null) {
			GL11.glRotatef(((TileEntityBlockDeployer)tile).blockRotation, 0, 1, 0);
			RenderUtil.renderEntityItem(tile.worldObj, tileBD.getStackInSlot(0), true);
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
