package electrodynamics.client.render.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.render.block.BlockRenderer;
import electrodynamics.item.ItemBlockWithNBT;

public class RenderItemBlockWithNBT extends BlockRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY: {
				renderBlock(item, 0F, 1F, 0F);
				break;
			}
			case EQUIPPED: {
				renderBlock(item, 1.0F, 1.0F, 1.0F);
				break;
			}
			case EQUIPPED_FIRST_PERSON: {
				renderBlock(item, 1.0F, 2.0F, 1.0F);
				break;
			}
			case INVENTORY: {
				renderBlock(item, 0F, 1F, 0F);
				break;
			}
			default: break;
		}
	}

	private void renderBlock(ItemStack stack, float transX, float transY, float transZ) {
		GL11.glPushMatrix();
		GL11.glTranslatef(transX, transY - 1, transZ);
		
		Object[] decodedStack = ItemBlockWithNBT.decode(stack);
		
		int id = (Integer)decodedStack[0];
		int meta = (Integer)decodedStack[1];
		NBTTagCompound nbt = null;
		
		if (decodedStack[2] != null) {
			nbt = (NBTTagCompound) decodedStack[2];
		}
		
		if (id > 0) {
			Minecraft.getMinecraft().renderEngine.bindTexture("/terrain.png");
			Block block = Block.blocksList[id];
			Minecraft.getMinecraft().renderGlobal.globalRenderBlocks.renderBlockAsItem(block, meta, 0F);
			
			if (nbt != null) {
				if (block.hasTileEntity(meta)) {
					TileEntity tile = block.createTileEntity(Minecraft.getMinecraft().theWorld, meta);
					tile.readFromNBT(nbt);
					TileEntityRenderer.instance.renderTileEntityAt(tile, 0, 0, 0, 0);
				}
			}
		}
		
		GL11.glPopMatrix();
	}
	
}
