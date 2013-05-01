package electrodynamics.core.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class RenderHoloProjector extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		double updown = (tile.worldObj.getWorldTime() % 50) / 25F;
		updown = (float) Math.sin(updown * 3.141593);
		updown *= 0.2;
		
		GL11.glTranslated(x + .5, y + 2.25 + updown, z + .5);
		
		RenderManager.instance.renderEntity(Minecraft.getMinecraft().renderViewEntity, partial);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
