package electrodynamics.client.render.tileentity;

import electrodynamics.client.model.ModelBasicKiln;
import electrodynamics.client.model.ModelKilnTray;
import electrodynamics.lib.client.Models;
import electrodynamics.tileentity.TileEntityBasicKiln;
import electrodynamics.tileentity.TileEntityMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;


public class RenderBasicKiln extends TileEntitySpecialRenderer {

	private ModelBasicKiln modelKiln;
	private ModelKilnTray modelTray;

	public RenderBasicKiln() {
		modelKiln = new ModelBasicKiln();
		modelTray = new ModelKilnTray();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glDisable( GL11.GL_LIGHTING );

		GL11.glColor4f( 1, 1, 1, 1 );
		GL11.glTranslated( x + 0.5, y + 1.5, z + 0.5 );
		GL11.glRotatef( 180, 0, 0, 1 );

		if( ((TileEntityMachine) tile).rotation != null ) {
			switch( ((TileEntityMachine) tile).rotation ) {
				case NORTH:
//					GL11.glRotatef(0, 0, 1, 0);
					break;
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

		TileEntityBasicKiln kiln = (TileEntityBasicKiln) tile;

		Minecraft.getMinecraft().renderEngine.bindTexture( Models.TEX_BASIC_KILN );
		modelKiln.rotateDoor( kiln.doorAngle );
		modelKiln.renderAll( 0.0625F );

		// todo: render fire and particles

		if( kiln.trayInventory != null ) { // render tray
			renderTray( kiln.worldObj, kiln.trayInventory.inventory );
		}

		GL11.glEnable( GL11.GL_LIGHTING );
		GL11.glPopMatrix();
	}

	public void renderTray(World world, ItemStack[] inv) {
		GL11.glTranslated( 0, -0.15f, 0 );
//		GL11.glRotatef( 0.0f, 0, 1, 0 );
//		GL11.glScaled( 1.0f, 1.0f, 1.0f );

		Minecraft.getMinecraft().renderEngine.bindTexture( Models.TEX_KILN_TRAY );
		modelTray.renderAll( 0.0625F );

		GL11.glRotatef( 270, 0, 1, 0 );

		if( inv != null && inv.length > 0 ) {
			GL11.glTranslated( -.11, 1.2, .11 );
			GL11.glScaled( .35, .35, .35 );

			for( int i = 0; i < inv.length; i++ ) {
				ItemStack stack = inv[i];
				if( stack == null )
					continue;

				if( i != 0 ) {
					GL11.glTranslated( .28, 0, 0 );
				}

				if( i == 3 || i == 6 ) {
					GL11.glTranslated( -.84, 0, -.38 );
				}

				if( !(stack.getItem() instanceof ItemBlock) ) {
					GL11.glPushMatrix();
					GL11.glScaled( .8, .8, .8 );
					GL11.glRotatef( 90, 1, 0, 0 );
					GL11.glTranslated( 0, -.24, -.26 );

					renderItem( world, stack );

					GL11.glPopMatrix();
				} else {
					GL11.glPushMatrix();
					GL11.glRotatef( 180, 0, 0, 1 );
					GL11.glTranslated( 0, -.2, 0 );

					renderItem( world, stack );

					GL11.glPopMatrix();
				}
			}
		}
	}

	public void renderItem(World world, ItemStack stack) {
		if (stack != null) {
			//Incredibly hackish, but better than essentially writing out a copy of the EntityItem renderer
			boolean fancy = Minecraft.getMinecraft().gameSettings.fancyGraphics;
			Minecraft.getMinecraft().gameSettings.fancyGraphics = true;

			EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
			entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;
			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);

			Minecraft.getMinecraft().gameSettings.fancyGraphics = fancy;
		}
	}

}
