package electrodynamics.client.render.tileentity;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelMetalTray;
import electrodynamics.client.model.ModelSinteringOven;
import electrodynamics.lib.client.Models;
import electrodynamics.tileentity.TileEntityMachine;
import electrodynamics.tileentity.TileEntitySinteringOven;
import electrodynamics.util.InventoryUtil;

public class RenderSinteringOven extends TileEntitySpecialRenderer {

	private ModelSinteringOven modelSinteringOven;
	private ModelMetalTray modelMetalTray;

	public RenderSinteringOven() {
		this.modelSinteringOven = new ModelSinteringOven();
		this.modelMetalTray = new ModelMetalTray();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180, 0, 0, 1);

		switch (((TileEntityMachine) tile).rotation) {
		case NORTH:
			GL11.glRotatef(270, 0, 1, 0);
			break;
		case SOUTH:
			GL11.glRotatef(90, 0, 1, 0);
			break;
		case WEST:
			GL11.glRotatef(180, 0, 1, 0);
			break;
		case EAST:
			// GL11.glRotatef(0, 0, 1, 0);
			break;
		default:
			break;
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_SINT_FURNACE);

		modelSinteringOven.rotateDoor(((TileEntitySinteringOven)tile).doorAngle);
		modelSinteringOven.renderAll(0.0625F);

		if (((TileEntitySinteringOven)tile).fuelLevel > 0) {
			renderFire(tile.worldObj, tile.xCoord, tile.yCoord, tile.zCoord, ((TileEntityMachine)tile).rotation.ordinal());
		}
		
		if (((TileEntitySinteringOven)tile).hasTray) {
			renderTray(tile.worldObj, InventoryUtil.getFirstItemInArray(((TileEntitySinteringOven)tile).trayInventory.inventory));
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	public void renderTray(World world, ItemStack stack) {
		Minecraft.getMinecraft().renderEngine.bindTexture(Models.TEX_METAL_TRAY);
		GL11.glTranslated(0, -0.5, 0);
		modelMetalTray.render(0.0625F);
		
		if (stack != null) {
			GL11.glTranslated(0, 1.4, -0.23);
			GL11.glRotatef(90, 1, 0, 0);
			
			if (stack.getItem() instanceof ItemBlock) {
				GL11.glRotatef(-90, 1, 0, 0);
				GL11.glRotatef(180, 1, 0, 0);
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslated(-0.23, 0, 0);
			}
			
			EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
			entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;
	        RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		}
	}
	
	public void renderFire(World world, int x, int y, int z, int rotation) {
		Random rand = new Random();
		
        float f = (float)x + 0.5F;
        float f1 = (float)y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
        float f2 = (float)z + 0.5F;
        float f4 = rand.nextFloat() * 0.6F - 0.3F;

        world.spawnParticle("smoke", f - (f4 / 2) + f4, f1, f2 - (f4 / 2) + f4, 0D, 0D, 0D);
        world.spawnParticle("flame", f - (f4 / 2) + f4, f1, f2 - (f4 / 2) + f4, 0D, 0D, 0D);
	}
	
}
