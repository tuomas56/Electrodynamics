package electrodynamics.client.render.tileentity;

import electrodynamics.client.model.ModelMobGrinder;
import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.lib.client.Models;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class RenderTileStructure extends TileEntitySpecialRenderer {

	private ModelSinteringFurnace sintFurnace;
	private ModelMobGrinder mobGrinder;

	public RenderTileStructure() {
		this.sintFurnace = new ModelSinteringFurnace();
		this.mobGrinder = new ModelMobGrinder();
	}

	public void renderStructureBlockAt(TileStructure structure, double x, double y, double z, float partial) {
		if ((structure).isCentralTileEntity()) {
			renderMBSAt(structure, x, y, z, partial);
		} else {
			if (structure.isValidStructure()) return;
			
			StructureComponent component = StructureComponent.values()[structure.getSubBlock()];
			
			if (component.getModel() != null) {
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				
				GL11.glTranslated(x + .5, y + .5, z + .5);
				
				Minecraft.getMinecraft().renderEngine.bindTexture(component.getModelTexture());
				component.applyGLTransformations((byte) 0);
				component.getModel().render(0.0625F);
				
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
			}
		}
	}

	public void renderMBSAt(TileStructure structure, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		MultiBlockStructure mbs = structure.getMBS();
		if (mbs != null) {
			if (mbs.getUID().equals("SintFurnace")) {
				GL11.glTranslated(x + .5, y + 1.5, z + .5);
				GL11.glRotatef(180, 1, 0, 0);
				int rotation = structure.getRotation();
				GL11.glRotatef(90 * (rotation / 2), 0, 1, 0);

				Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_SINT_OVEN);

				GL11.glColor4f(1, 1, 1, 1);
				sintFurnace.render(0.0625F);
			} else if (mbs.getUID().equals("MobGrinder")) {
				GL11.glTranslated(x + .5, y + 1.5, z + .5);
				GL11.glRotatef(180, 1, 0, 0);
				int rotation = structure.getRotation();
				GL11.glRotatef(90 * (rotation / 2), 0, 1, 0);

				Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_MOB_GRINDER);

				GL11.glColor4f(1, 1, 1, 1);
				mobGrinder.render(0.0625F);
			}
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		renderStructureBlockAt((TileStructure) tileentity, d0, d1, d2, f);
	}

}
