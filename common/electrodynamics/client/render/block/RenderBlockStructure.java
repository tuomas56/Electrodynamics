package electrodynamics.client.render.block;


import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class RenderBlockStructure implements ISimpleBlockRenderingHandler {

	public RenderBlockStructure() {
		init();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glDisable( GL11.GL_ALPHA_TEST );

		setOrientation( renderer, ForgeDirection.NORTH );

		renderer.setRenderBoundsFromBlock( block );
		BlockRenderer.drawFaces( renderer, block, metadata, true );

		setOrientation( renderer, null );

		GL11.glEnable( GL11.GL_ALPHA_TEST );
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TileEntity tileEntity = world.getBlockTileEntity( x, y, z );
		if( tileEntity == null || !(tileEntity instanceof TileStructure) )
			return true;

		TileStructure tile = (TileStructure) tileEntity;
		// re-enable this after the MBS rendering is done.
//		if( tile.isValidStructure() ) {
//			// let the TESR handle the rendering.
//			return true;
//		}
		int sub = tile.getSubBlock();
		if( sub == -1 )
			return true; // deal with invalid type later.

		int metadata = world.getBlockMetadata( x, y, z );

		if( sub == 0 ) // conveyor belt
			renderRotatedBlock( renderer, block, x, y, z, ForgeDirection.getOrientation( metadata ) );
		else
			renderer.renderStandardBlock( block, x, y, z );

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return ConfigurationSettings.STRUCTURE_BLOCK_RENDER_ID;
	}


	int[][] rotationMatrix = new int[6][6]; // metadata X bottom, top, east, west, north, south


	void init() {
		rotationMatrix[ForgeDirection.NORTH.ordinal()] = new int[] { 3, 0, 3, 0, 1, 2 };
		rotationMatrix[ForgeDirection.SOUTH.ordinal()] = new int[] { 0, 3, 0, 3, 2, 0 }; // last pair is wrong.
		rotationMatrix[ForgeDirection.WEST.ordinal()] = new int[] { 2, 2, 2, 1, 3, 0 };
		rotationMatrix[ForgeDirection.EAST.ordinal()] = new int[] { 1, 1, 2, 2, 0, 3 }; // middle pair is wrong.
	}

	public void renderRotatedBlock(RenderBlocks renderer, Block block, int x, int y, int z, ForgeDirection front) {
		setOrientation( renderer, front );
		renderer.renderStandardBlock( block, x, y, z );
		setOrientation( renderer, null );
	}

	private void setRotationUV(RenderBlocks renderer, int[] rotations) {
		renderer.uvRotateBottom = rotations[0]; // -Y
		renderer.uvRotateTop = rotations[1];    // +Y
		renderer.uvRotateEast = rotations[2];   // -Z
		renderer.uvRotateWest = rotations[3];   // +Z
		renderer.uvRotateNorth = rotations[4];  // -X
		renderer.uvRotateSouth = rotations[5];  // +X
	}

	public void setOrientation(RenderBlocks renderer, ForgeDirection orientation) {
		if( orientation == null || orientation == ForgeDirection.UNKNOWN ) {
			setRotationUV( renderer, new int[] { 0, 0, 0, 0, 0, 0 } );
		} else {
			setRotationUV( renderer, rotationMatrix[orientation.ordinal()] );
		}
	}

}
