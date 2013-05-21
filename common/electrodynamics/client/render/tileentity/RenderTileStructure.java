package electrodynamics.client.render.tileentity;

import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.lib.client.Models;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderTileStructure extends TileEntitySpecialRenderer {

	private ModelSinteringFurnace sintFurnace;

	public RenderTileStructure() {
		this.sintFurnace = new ModelSinteringFurnace();
	}

	public void renderMBSAt(TileStructure structure, double x, double y, double z, float partial) {
		MultiBlockStructure mbs = structure.getMBS();
		if( mbs != null && mbs.getUID().equals( "SintFurnace" ) ) {
			GL11.glPushMatrix();
			GL11.glDisable( GL11.GL_LIGHTING );
			GL11.glTranslated( x + .5, y + 1.5, z + .5 );
			GL11.glRotatef(180, 1, 0, 0);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_SINT_OVEN);
			
			GL11.glColor4f(1, 1, 1, 1);
			sintFurnace.render( 0.0625F );

			GL11.glEnable( GL11.GL_LIGHTING );
			GL11.glPopMatrix();
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		if( ((TileStructure) tileentity).isCentralTileEntity() ) {
			renderMBSAt( (TileStructure) tileentity, d0, d1, d2, f );
		}
	}

}
