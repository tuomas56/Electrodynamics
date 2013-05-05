package electrodynamics.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.BlockRedWire;
import electrodynamics.tileentity.TileEntityRedWire;

public class RenderBlockRedWire implements ISimpleBlockRenderingHandler {

	public static int renderID;
	
	static {
		renderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TileEntityRedWire redWire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
		
		float minSize = BlockRedWire.wireMinSize;
		float maxSize = BlockRedWire.wireMaxSize;
		
		block.setBlockBounds(minSize, minSize, minSize, maxSize, maxSize, maxSize);
		renderer.setRenderBoundsFromBlock(block);
		renderer.renderStandardBlock(block, x, y, z);
		
		if (redWire.isConnected(ForgeDirection.WEST)) {
			block.setBlockBounds(0.0F, minSize, minSize, minSize, maxSize, maxSize);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		if (redWire.isConnected(ForgeDirection.EAST)) {
			block.setBlockBounds(maxSize, minSize, minSize, 1.0F, maxSize, maxSize);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		if (redWire.isConnected(ForgeDirection.DOWN)) {
			block.setBlockBounds(minSize, 0.0F, minSize, maxSize, minSize, maxSize);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		if (redWire.isConnected(ForgeDirection.UP)) {
			block.setBlockBounds(minSize, maxSize, minSize, maxSize, 1.0F, maxSize);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		if (redWire.isConnected(ForgeDirection.NORTH)) {
			block.setBlockBounds(minSize, minSize, 0.0F, maxSize, maxSize, minSize);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		if (redWire.isConnected(ForgeDirection.SOUTH)) {
			block.setBlockBounds(minSize, minSize, maxSize, maxSize, maxSize, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			renderer.renderStandardBlock(block, x, y, z);
		}

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
