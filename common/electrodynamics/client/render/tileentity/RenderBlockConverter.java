package electrodynamics.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelConverter;
import electrodynamics.tileentity.machine.TileEntityMachine;
import electrodynamics.tileentity.machine.utilty.TileEntityConverter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderBlockConverter extends TileEntitySpecialRenderer {

	private ModelConverter model;
	
	public RenderBlockConverter() {
		this.model = new ModelConverter();
	}
	
	public void renderModel(TileEntityConverter tile, double x, double y, double z) {
		if( ((TileEntityMachine) tile).rotation != null ) {
			switch( ((TileEntityMachine) tile).rotation ) {
				case SOUTH:
					GL11.glRotatef( 180, 0, 1, 0 );
					break;
				case WEST:
					GL11.glRotatef( 270, 0, 1, 0 );
					break;
				case EAST:
					GL11.glRotatef( 90, 0, 1, 0 );
					break;
				default:
					break;
			}
		}
		
		this.model.render(0.0625F);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslated(d0 + .5, d1 - .5, d2 + .5);
		
		if (tileentity != null) {
			renderModel((TileEntityConverter) tileentity, d0, d1, d2);
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
