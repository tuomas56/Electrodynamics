package electrodynamics.client.render.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import electrodynamics.block.BlockOre;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.lib.block.Ore;

public class RenderBlockOre extends BlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		
		if ((metadata == Ore.VOIDSTONE.ordinal())) {
			Tessellator t = Tessellator.instance;
			t.setBrightness(320);

			block.setBlockBounds(0.1F, 0.1F, 0.1F, 0.9F, 0.9F, 0.9F);
			drawFaces(renderer, block, ((BlockOre) block).voidstoneTexture, true);
			
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			drawFaces(renderer, block, ((BlockOre) block).oreTransparency, true);
		} else {
			drawFaces(renderer, block, ((BlockOre) block).textures[metadata], true);
		}
		
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	@SuppressWarnings("unused")
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int bb = setBrightness(world, x, y, z, block);
	    int metadata = world.getBlockMetadata(x, y, z);
	    
	    if ((metadata == Ore.VOIDSTONE.ordinal())) {
	      Tessellator t = Tessellator.instance;
	      t.setBrightness(320);

	      block.setBlockBounds(0.2F, 0.2F, 0.2F, 0.8F, 0.8F, 0.8F);
	      renderAllSides(world, x, y, z, block, renderer, ((BlockOre)block).voidstoneTexture);
	    }

	    block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    renderer.setRenderBoundsFromBlock(block);
	    renderer.renderStandardBlock(block, x, y, z);
	    renderer.clearOverrideBlockTexture();
	    block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    renderer.setRenderBoundsFromBlock(block);
	    return true;
	}

	public boolean shouldRender3DInInventory() {
		return true;
	}

	public int getRenderId() {
		return ConfigurationSettings.VOIDSTONE_RENDER_ID;
	}

}
