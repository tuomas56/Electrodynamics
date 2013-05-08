package electrodynamics.client.render.block;

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
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);

		if (metadata == Ore.VOIDSTONE.ordinal()) {
			drawFaces(renderer, block, ((BlockOre) block).voidstoneTextures[1], false);
		} else {
			drawFaces(renderer, block, ((BlockOre) block).textures[metadata], false);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int metadata = world.getBlockMetadata(x, y, z);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);

		if ((metadata == Ore.VOIDSTONE.ordinal())) {
			if (ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS) {
				Tessellator t = Tessellator.instance;
				t.setBrightness(160);

				//Effect
			}
		}

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
