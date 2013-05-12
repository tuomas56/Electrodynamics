package electrodynamics.client.render.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import electrodynamics.client.model.ModelTreeTap;
import electrodynamics.lib.client.Models;

public class RenderItemTreetap implements IItemRenderer {

	private ModelTreeTap modelTreetap;
	
	public RenderItemTreetap() {
		this.modelTreetap = new ModelTreeTap();
	}
	
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
		switch(type) {
			case ENTITY: {
				renderTreetap(0.8F, 1F, 0F);
				break;
			}
			
			case EQUIPPED: {
				renderTreetap(1.5F, 0F, 1.0F);
				break;
			}
			
			case INVENTORY: {
				renderTreetap(0.6F, 0F, 0F);
				break;
			}
			
			default: break;
		}
	}

	private void renderTreetap(float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_TREETAP);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(2, 2, 2);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);

		modelTreetap.render(0.0625F);
		
		GL11.glPopMatrix();
	}
	
}
