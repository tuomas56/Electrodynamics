package electrodynamics.block;

import electrodynamics.world.TickHandlerMBS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Blocks used for MBS
 */
public abstract class BlockStructure extends BlockContainer {

	public BlockStructure(int blockID, Material material) {
		super( blockID, material );
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata) {
		scheduleUpdate( world, x, y, z );
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		Block block = Block.blocksList[neighborID];
		if( block != null && block instanceof BlockStructure ) {
			scheduleUpdate( world, x, y, z );
		}
	}

	protected void scheduleUpdate(World world, int x, int y, int z) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z );
	}

	public static boolean isStructureBlock(IBlockAccess world, int x, int y, int z) {
		return isStrutureBlock( world.getBlockId( x, y, z ) );
	}

	public static boolean isStrutureBlock(int blockID) {
		return blockID != 0 && Block.blocksList[blockID] instanceof BlockStructure;
	}

}
