package electrodynamics.mbs.util;

import electrodynamics.mbs.WorldCoordinate;
import net.minecraft.world.IBlockAccess;

import java.util.Iterator;

/**
 * The representation of a 3D portion of the world, of fixed size.
 * This is unrelated to Minecraft's Chunk.
 * <p/>
 * Used to iterate through the blocks contained in this chunk and compare them with the MBS patterns.
 */
public class WorldChunk implements Iterable<WorldBlock> {

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

	public IBlockAccess getBlockAccess() {
		return access;
	}

	public static WorldChunk getChunk(WorldCoordinate start, WorldCoordinate end) {
		int x = Math.min( start.x, end.x );
		int y = Math.min( start.y, end.y );
		int z = Math.min( start.z, end.z );

		int width = Math.max( start.x, end.x ) - x + 1;
		int height = Math.max( start.y, end.y ) - y + 1;
		int depth = Math.max( start.z, end.z ) - z + 1;

		return new WorldChunk( start.getBlockAccess(), x, y, z, width, height, depth );
	}

	public WorldCoordinate getBaseCoordinates() {
		return new WorldCoordinate( access, x, y, z );
	}

	@Override
	public Iterator<WorldBlock> iterator() {
		return new WorldChunkIterator();
	}

	private class WorldChunkIterator implements Iterator<WorldBlock> {

		int i = 0;
		int j = 0;
		int k = 0;

		@Override
		public boolean hasNext() {
			return i < width && j < height && k < depth;
		}

		@Override
		public WorldBlock next() {
			WorldBlock block = getBlockAt( i++, j, k );
			if( i >= width ) {
				j++;
				i = 0;
			}
			if( j >= height ) {
				k++;
				j = 0;
			}
			return block;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException( "remove" );
		}
	}
}
