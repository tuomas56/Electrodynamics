package electrodynamics.client.render.tileentity;

import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.tileentity.TileStructure;
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
			GL11.glDisable( GL11.GL_LIGHTING );
			GL11.glTranslated( x, y, z );

			//((ModelSinteringFurnace)mbs.getModel()).render(0.0625F);
			sintFurnace.render( 0.0625F );

			GL11.glEnable( GL11.GL_LIGHTING );
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		if( ((TileStructure) tileentity).isCentralTileEntity() ) {
			renderMBSAt( (TileStructure) tileentity, d0, d1, d2, f );
		}
	}

}
