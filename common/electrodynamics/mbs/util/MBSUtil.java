package electrodynamics.mbs.util;


import electrodynamics.block.BlockStructure;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

import java.util.Arrays;

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

	/**
	 * Whether if the specified region contains at least one structure block.
	 *
	 * @param coords the coordinates of any
	 * @param deltaX the change in the x-axis relative to <code>coords.x</code>
	 * @param deltaY the change in the y-axis relative to <code>coords.y</code>
	 * @param deltaZ the change in the z-axis relative to <code>coords.z</code>
	 * @param width  the width of the region to test. Must be higher than 0.
	 * @param height the height of the region to test. Must be higher than 0.
	 * @param depth  the depth of the region to test. Must be higher than 0.
	 * @return true if <code>isStructureBlock()</code> returns true for at least one block in the specified region.
	 */
	public static boolean containsStructureBlock(WorldCoordinate coords, int deltaX, int deltaY, int deltaZ, int width, int height, int depth) {
		for( int i = 0; i < width; i++ ) {
			int x = coords.x + deltaX + i;
			for( int j = 0; j < height; j++ ) {
				int y = coords.y + deltaY + j;
				for( int k = 0; k < depth; k++ ) {
					int z = coords.z + deltaZ + k;

					if( MBSUtil.isStructureBlock( coords.getBlockAccess(), x, y, z ) )
						return true;
				}
			}
		}
		return false;
	}


	private static final int lowerX = 0;
	private static final int upperX = 1;
	private static final int lowerY = 2;
	private static final int upperY = 3;
	private static final int lowerZ = 4;
	private static final int upperZ = 5;

	/**
	 * Gets the region that encloses a structure that is potentially a MBS.
	 * <p/>
	 * The region grows in width, height and depth recursively
	 * as long as there is a structure block within the immediate surroundings of the chunk.
	 *
	 * @param coords the initial coordinates.
	 */
	public static WorldChunk getChunkSurrounding(WorldCoordinate coords) {
		int[] measures = new int[] { 0, 0, 0, 0, 0, 0 };
		int[] newMeasures;
		int i = 0; // to prevent infinite loops. max dimensions: 25x25x25

		// get the measures by recursion
		while( i++ < 25 && !Arrays.equals( measures, newMeasures = getNewMeasures( coords, measures ) ) ) {
			measures = newMeasures;
		}

		WorldCoordinate start = coords.translate( -measures[lowerX], -measures[lowerY], -measures[lowerZ] );
		WorldCoordinate end = coords.translate( measures[upperX], measures[upperY], measures[upperZ] );

		return WorldChunk.getChunk( start, end );
	}

	private static int[] getNewMeasures(WorldCoordinate coords, int[] measures) {
		int width = measures[lowerX] + measures[upperX] + 1;
		int height = measures[lowerY] + measures[upperY] + 1;
		int depth = measures[lowerZ] + measures[upperZ] + 1;

		int[] array = measures.clone();

		// check X-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX] - 1, -measures[lowerY], -measures[lowerZ], 1, height, depth ) ) {
			array[lowerX]++;
		}
		// check X-axis (upper)
		if( containsStructureBlock( coords, measures[upperX] + 1, -measures[lowerY], -measures[lowerZ], 1, height, depth ) ) {
			array[upperX]++;
		}

		// check Y-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY] - 1, -measures[lowerZ], width, 1, depth ) ) {
			array[lowerY]++;
		}
		// check Y-axis (upper)
		if( containsStructureBlock( coords, -measures[lowerX], measures[upperY] + 1, -measures[lowerZ], width, 1, depth ) ) {
			array[upperY]++;
		}

		// check Z-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY], -measures[lowerZ] - 1, width, height, 1 ) ) {
			array[lowerZ]++;
		}
		// check Z-axis (upper)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY], measures[upperZ] + 1, width, height, 1 ) ) {
			array[upperZ]++;
		}

		return array;
	}

}
