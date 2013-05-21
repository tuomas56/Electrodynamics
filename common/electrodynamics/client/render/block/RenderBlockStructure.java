package electrodynamics.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class RenderBlockStructure implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		int[] rotations = getRotations(metadata, ForgeDirection.NORTH.ordinal());
		if (rotations.length == 6) {
			setRotationUV(renderer, rotations); // set UV rotations
		}
		renderer.setRenderBoundsFromBlock(block);
		BlockRenderer.drawFaces(renderer, block, metadata, true);
		setRotationUV(renderer, null); // clear UV rotations

		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		TileEntity tileEntity = world.getBlockTileEntity( x, y, z );
		if( tileEntity == null || !(tileEntity instanceof TileStructure) )
			return true;

		TileStructure tile = (TileStructure) tileEntity;
		if( tile.isValidStructure()) {
			return true;
		}
		int sub = tile.getSubBlock();
		if( sub == -1 )
			return true; // deal with invalid type later.

		int metadata = world.getBlockMetadata( x, y, z );
		int[] rotations = getRotations( sub, metadata );
		if( rotations.length == 6 ) {
			setRotationUV( renderer, rotations ); // set UV rotations
		}
		renderer.renderStandardBlock( block, x, y, z );
		setRotationUV( renderer, null ); // clear UV rotations
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

	private int[] getRotations(int subBlock, int orientation) {
		return StructureComponent.values()[subBlock].getRotationMatrix()[orientation];
	}

	private void setRotationUV(RenderBlocks renderer, int[] rotations) {
		if (rotations == null || rotations.length != 6)
			rotations = new int[6]; // on invalid input, clear UV rotations.

		renderer.uvRotateBottom = rotations[0]; // -Y
		renderer.uvRotateTop = rotations[1]; // +Y
		renderer.uvRotateEast = rotations[2]; // -Z
		renderer.uvRotateWest = rotations[3]; // +Z
		renderer.uvRotateNorth = rotations[4]; // -X
		renderer.uvRotateSouth = rotations[5]; // +X
	}

}
