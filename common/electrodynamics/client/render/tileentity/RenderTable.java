package electrodynamics.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelStoneTable;
import electrodynamics.client.model.ModelTable;
import electrodynamics.client.model.ModelWoodTable;
import electrodynamics.client.render.util.RenderUtil;
import electrodynamics.lib.client.Textures;
import electrodynamics.tileentity.machine.TileEntityTable;

public class RenderTable extends TileEntitySpecialRenderer {

	private ModelTable[] tables = new ModelTable[2];
	
	private ResourceLocation[] textures = new ResourceLocation[] {Textures.TABLE_WOOD.resource, Textures.TABLE_STONE.resource};

	public RenderTable() {
		tables[0] = new ModelWoodTable();
		tables[1] = new ModelStoneTable();
	}
	
	public void renderTableContentsAt(TileEntityTable table) {
		ItemStack itemstack = table.displayedItem;

		if (itemstack != null) {
			byte type = (byte)table.worldObj.getBlockMetadata(table.xCoord, table.yCoord, table.zCoord);
			if (itemstack.getItem() instanceof ItemMinecart) {
				renderMinecart(table.worldObj, itemstack, type);
			} else {
				renderItem(table.worldObj, itemstack, type);
			}
		}
	}

	private void renderMinecart(World world, ItemStack stack, byte type) {
		int minecartType = ((ItemMinecart)stack.getItem()).minecartType;
		
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScaled(.4, .4, .4);
		GL11.glTranslated(0, -1.2, 0);
		
		if (type == 0) {
			GL11.glTranslated(0, -0.5, 0);
		}
		
		EntityMinecart minecart = EntityMinecart.createMinecart(world, 0, 0, 0, minecartType);
		
		RenderItem.renderInFrame = true;
        RenderManager.instance.renderEntityWithPosYaw(minecart, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        RenderItem.renderInFrame = false;
	}
	
	private void renderItem(World world, ItemStack stack, byte type) {
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glTranslated(0, -0.62, 0);
		
		if (!(stack.getItem() instanceof ItemBlock)) {
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslated(0, -0.18, 0);
		} else {
			GL11.glScaled(1.4, 1.4, 1.4);
			GL11.glTranslated(0, 0.01, 0);
		}
		
		if (type == 0) {
			if ((stack.getItem() instanceof ItemBlock)) {
				GL11.glTranslated(0, -0.13, 0);
			} else {
				GL11.glTranslated(0, 0, 0.18);
			}
		}
		
		GL11.glScalef(1.25F, 1.25F, 1.25F);
		
		RenderUtil.renderEntityItem(world, stack, true);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partial) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
		GL11.glRotatef(180, 0, 0, 1);
		
		byte type = (byte)tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

		Minecraft.getMinecraft().func_110434_K().func_110577_a(textures[type]);
		tables[type].render(0.0625F);
		
		renderTableContentsAt((TileEntityTable)tile);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
