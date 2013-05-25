package electrodynamics.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import electrodynamics.client.model.ModelBasicSieve;
import electrodynamics.client.model.ModelSinteringOven;
import electrodynamics.lib.client.Models;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderItemMachine implements IItemRenderer {

	private ModelSinteringOven modelSinteringOven;
	private ModelBasicSieve modelBasicSieve;
	
	public RenderItemMachine() {
		this.modelSinteringOven = new ModelSinteringOven();
		this.modelBasicSieve = new ModelBasicSieve();
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
		if (item.getItemDamage() == 0) {
			switch(type) {
				case ENTITY: {
					renderFurnace(0F, 1F, 0F);
					break;
				}

				case EQUIPPED: {
					renderFurnace(1.0F, 1.0F, 1.0F);
					break;
				}

				case EQUIPPED_FIRST_PERSON: {
					renderFurnace(1.0F, 2.0F, 1.0F);
					break;
				}
				
				case INVENTORY: {
					renderFurnace(0F, 1F, 0F);
					break;
				}
				
				default: break;
			}
		} else if (item.getItemDamage() == 1) {
			switch(type) {
				case ENTITY: {
					renderSieve(0F, 1F, 0F);
					break;
				}
				
				case EQUIPPED: {
					renderSieve(1.0F, 1.0F, 1.0F);
					break;
				}

				case EQUIPPED_FIRST_PERSON: {
					renderSieve(1.0F, 2.0F, 1.0F);
					break;
				}

				case INVENTORY: {
					renderSieve(0F, 1F, 0F);
					break;
				}
				
				default: break;
			}
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
	
	private void renderSieve(float x, float y, float z) {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(Models.TEX_BASIC_SIEVE);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(-90, 0, 1, 0);
		modelBasicSieve.renderAll(0.0625F);
		GL11.glPopMatrix();
	}
	
}
