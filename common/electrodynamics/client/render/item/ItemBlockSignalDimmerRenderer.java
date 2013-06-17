package electrodynamics.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import electrodynamics.client.model.ModelSignalDimmer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemBlockSignalDimmerRenderer implements IItemRenderer{
	private final ModelSignalDimmer model;
	
	public ItemBlockSignalDimmerRenderer(){
		this.model = new ModelSignalDimmer();
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
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		switch(type)
		{
		case ENTITY:
			renderSignalDimmer(0.5F, 1.5F, 0.5F, 0.5F);
			break;
		case EQUIPPED:
			renderSignalDimmer(0.4F, -1.0F, 0.4F, 1.0F);
			break;
		case INVENTORY:
			renderSignalDimmer(0.5F, -1.0F, 0.5F, 1.5F);
			break;
		default:
			break;
		}
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	private void renderSignalDimmer(float x, float y, float z, float scale){
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/electrodynamics/textures/misc/signalDimmer.png");
		model.renderAllButWires(0.0625F);
	}
}