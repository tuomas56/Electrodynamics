package electrodynamics.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import electrodynamics.client.model.ModelSinteringOven;
import electrodynamics.lib.core.Models;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemSinteringOven implements IItemRenderer {

	private ModelSinteringOven modelSinteringOven;
	
	public RenderItemSinteringOven() {
		this.modelSinteringOven = new ModelSinteringOven();
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
				renderFurnace(0F, 1F, 0F);
				break;
			}
			
			case EQUIPPED: {
				renderFurnace(1.0F, 1.0F, 1.0F);
				break;
			}
			
			case INVENTORY: {
				renderFurnace(0F, 1.075F, 0F);
				break;
			}
			
			default: break;
		}
	}

	private void renderFurnace(float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_SINT_FURNACE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		modelSinteringOven.renderAll(0.0625F);
		GL11.glPopMatrix();
	}
	
}
