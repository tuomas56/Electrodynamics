package electrodynamics.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.block.BlockUtility;
import electrodynamics.lib.block.UtilityBlock;
import electrodynamics.tileentity.machine.TileEntityMachine;

public class RenderBlockUtility extends BlockRenderer implements ISimpleBlockRenderingHandler {

	public static int renderID;
	
	static {
		renderID = RenderingRegistry.getNextAvailableRenderId();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);

		if (metadata == UtilityBlock.BLOCK_DEPLOYER.ordinal()) {
			BlockUtility util = (BlockUtility)block;
			drawFaces(renderer, block, util.textures[0][0], util.textures[0][1], util.textures[0][2], false);
		} else {
			drawFaces(renderer, block, metadata, false);
		}
		
		GL11.glPopMatrix();
	}

	@SuppressWarnings("unused")
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		int bb = setBrightness(world, x, y, z, block);
	    int metadata = world.getBlockMetadata(x, y, z);
		
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    renderer.setRenderBoundsFromBlock(block);
	    renderer.renderStandardBlock(block, x, y, z);
	    renderer.clearOverrideBlockTexture();
	    block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	    renderer.setRenderBoundsFromBlock(block);

	    if (metadata == UtilityBlock.BLOCK_DEPLOYER.ordinal()) {
	    	TileEntity tile = world.getBlockTileEntity(x, y, z);
		    ForgeDirection side = ((TileEntityMachine)tile).rotation;
		    
		    Icon front = ((BlockUtility)block).textures[UtilityBlock.BLOCK_DEPLOYER.ordinal()][0];
		    Icon back = ((BlockUtility)block).textures[UtilityBlock.BLOCK_DEPLOYER.ordinal()][1];
		    
		    switch(side) {
			    case UP: {
			    	renderer.renderFaceYNeg(block, x, y + 1, z, front);
			    	renderer.renderFaceYPos(block, x, y - 1, z, back);
			    	break;
			    }
			    case DOWN: {
			    	renderer.renderFaceYNeg(block, x, y + 1, z, front);
			    	renderer.renderFaceYPos(block, x, y - 1, z, back);
			    	break;
			    }
				case EAST: {
					renderer.renderFaceXNeg(block, x + 1, y, z, front);
			    	renderer.renderFaceXPos(block, x - 1, y, z, back);
			    	break;
				}
				case WEST: {
					renderer.renderFaceXNeg(block, x + 1, y, z, front);
			    	renderer.renderFaceXPos(block, x - 1, y, z, back);
			    	break;
				}
				case NORTH: {
					renderer.renderFaceZNeg(block, x, y, z + 1, front);
			    	renderer.renderFaceZPos(block, x, y, z - 1, back);
			    	break;
				}
				case SOUTH: {
					renderer.renderFaceZNeg(block, x, y, z + 1, front);
			    	renderer.renderFaceZPos(block, x, y, z - 1, back);
			    	break;
				}
				case UNKNOWN: break;
				default: break;
			    }
	    }
	    
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

}
