package electrodynamics.mbs.util;


import electrodynamics.block.BlockStructure;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public final class MBSUtil {

	/**
	 * Whether if a block can be considered able to be part of Multi-block structure.
	 *
	 * @param blockID the ID of the block. Must be lower than 4096.
	 * @see Block#blocksList#length
	 */
	public static boolean isStructureBlock(int blockID) {
		return blockID != 0 && Block.blocksList[blockID] instanceof BlockStructure;
	}

	/**
	 * Whether if a block can be considered able to be part of Multi-block structure.
	 *
	 * @param access the access interface of a World object.
	 * @param x      the block's x coordinate.
	 * @param y      the block's y coordinate.
	 * @param z      the block's z coordinate.
	 */
	public static boolean isStructureBlock(IBlockAccess access, int x, int y, int z) {
		int id = access.getBlockId( x, y, z );
		return isStructureBlock( id );
	}


}
