package electrodynamics.world.mbs.util;

import electrodynamics.world.mbs.WorldCoordinate;
import net.minecraft.world.IBlockAccess;

/**
 * The representation of a 3D portion of the world, of fixed size.
 * This is unrelated to Minecraft's Chunk.
 */
public class WorldChunk {

	private IBlockAccess access;
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	private int depth;

	public WorldChunk(IBlockAccess access, int x, int y, int z, int width, int height, int depth) {
		this.access = access;
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

	public WorldBlock getBlockAt(int x, int y, int z) {
		if( x >= width || y >= height || z >= depth )
			return null;
		return new WorldBlock( access, this.x + x, this.y + y, this.z + z );
	}

	public static WorldChunk getChunk(WorldCoordinate start, WorldCoordinate end) {
		int x = Math.min( start.x, end.x );
		int y = Math.min( start.y, end.y );
		int z = Math.min( start.z, end.z );

		int width = Math.max( start.x, end.x );
		int height = Math.max( start.y, end.y );
		int depth = Math.max( start.z, end.z );

		return new WorldChunk( start.getBlockAccess(), x, y, z, width, height, depth );
	}
}
