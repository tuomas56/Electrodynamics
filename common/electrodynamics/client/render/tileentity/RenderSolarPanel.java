package electrodynamics.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelSolarPanel;
import electrodynamics.client.render.util.RenderUtil;
import electrodynamics.lib.client.Textures;
import electrodynamics.tileentity.machine.energy.TileEntitySolarPanel;

public class RenderSolarPanel extends TileEntitySpecialRenderer {

	private ModelSolarPanel solarPanel;
	
	public RenderSolarPanel() {
		this.solarPanel = new ModelSolarPanel();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(d0 + 0.5, d1 + 1.5, d2 + 0.5);
		
		GL11.glRotatef(180, 1, 0, 0);
		
		RenderUtil.bindTexture(Textures.SOLAR_PANEL.resource);
		
		this.solarPanel.rotatePanel(0, 0, ((TileEntitySolarPanel)tileentity).currAngle);
		
		this.solarPanel.render(0.0625F);
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
}
