package electrodynamics.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import electrodynamics.client.model.ModelStoneTable;
import electrodynamics.client.model.ModelTable;
import electrodynamics.client.model.ModelWoodTable;
import electrodynamics.lib.client.Models;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemTable implements IItemRenderer {

	private ModelTable[] tables = new ModelTable[2];
	
	private String[] textures = new String[] {Models.TEX_TABLE_WOOD, Models.TEX_TABLE_STONE};
	

	public RenderItemTable() {
		tables[0] = new ModelWoodTable();
		tables[1] = new ModelStoneTable();
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
				renderTable((byte) item.getItemDamage(), 0F, 1F, 0F);
				break;
			}
			
			case EQUIPPED: {
				renderTable((byte) item.getItemDamage(), 1.0F, 1.0F, 1.0F);
				break;
			}
			
			case INVENTORY: {
				renderTable((byte) item.getItemDamage(), 0F, 1F, 0F);
				break;
			}
			
			default: break;
		}
	}

	private void renderTable(byte type, float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(textures[type]);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		tables[type].render(0.0625F);
		GL11.glPopMatrix();
	}
	
}
