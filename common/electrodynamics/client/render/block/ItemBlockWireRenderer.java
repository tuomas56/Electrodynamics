package electrodynamics.client.render.block;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.client.model.ModelWire;

@SideOnly(Side.CLIENT)
public final class ItemBlockWireRenderer implements IItemRenderer{
	private final ModelWire model;
	
	public ItemBlockWireRenderer(){
		this.model = new ModelWire();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type){
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper){
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data){
		switch(type)
		{
		case ENTITY:
			renderWire(0.5F, 1.5F, 0.5F, 0.5F);
			break;
		case EQUIPPED:
			renderWire(0.5F, 1.5F, 0.5F, 0.5F);
			break;
		case INVENTORY:
			renderWire(0.5F, -1.5F, 0.5F, 1.5F);
			break;
		default:
			break;
		}
	}
	
	private void renderWire(float x, float y, float z, float scale){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/electrodynamics/textures/misc/redAlloyWire.png");
		model.renderAll(0.0625F);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}
