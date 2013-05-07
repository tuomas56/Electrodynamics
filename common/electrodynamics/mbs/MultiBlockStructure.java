package electrodynamics.mbs;


import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.tileentity.TileEntity;

public abstract class MultiBlockStructure {

	private int width;
	private int height;
	private int depth;
	private Pattern pattern;

	private boolean isSymmetricXZ;

	public MultiBlockStructure(Pattern pattern) {
		this( pattern, false );
	}

	public MultiBlockStructure(Pattern pattern, boolean isSymmetricXZ) {
		this.pattern = pattern;
		this.width = pattern.getWidth();
		this.height = pattern.getHeight();
		this.depth = pattern.getDepth();
		this.isSymmetricXZ = isSymmetricXZ;
	}

	public Pattern getPattern() {
		return pattern;
	}

	/**
	 * Checks if this MBS is found within the passed WorldChunk.
	 *
	 * @param chunk the chunk of blocks to be checked.
	 * @return an integer representing the rotation of the MBS, or -1 if not found.
	 */
	public int check(WorldChunk chunk) {

		// Compare dimensions:
		if( !compareDimensions( chunk ) )
			return -1;

		// Compare each block.

		boolean[] angles = new boolean[] { true, true, true, true };

		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				for( int z = 0; z < depth; z++ ) {
					WorldBlock worldBlock = chunk.getBlockAt( x, y, z );

					if( angles[0] && !pattern.getBlockAt( x, y, z ).isMatchingBlock( worldBlock ) )
						angles[0] = false;

					if( !isSymmetricXZ ) {
						if( angles[1] && !pattern.getBlockAt( depth - x, y, z ).isMatchingBlock( worldBlock ) )
							angles[1] = false;

						if( angles[2] && !pattern.getBlockAt( width - x, y, depth - z ).isMatchingBlock( worldBlock ) )
							angles[2] = false;

						if( angles[3] && !pattern.getBlockAt( x, y, width - z ).isMatchingBlock( worldBlock ) )
							angles[3] = false;
					}
				}
			}
		}

		if( isSymmetricXZ )
			return angles[0] ? 0 : -1;
		for( int i = 0; i < 4; i++ ) {
			if( angles[i] )
				return i;
		}
		return -1;
	}

	protected boolean compareDimensions(WorldChunk chunk) {
		if( chunk.getHeight() != height )
			return false;

		return chunk.getWidth() == width && chunk.getDepth() == depth
				|| chunk.getWidth() == depth && chunk.getDepth() == width;
	}

	public void initialize(WorldChunk chunk, int rotation) {
		WorldCoordinate coords = getCentralCoordinate( chunk, rotation );
		WorldBlock block = chunk.getBlockAt( coords.x, coords.y, coords.z );
		if( block == null ) {
			coords.translate( 0, -1, 0 );
			block = chunk.getBlockAt( coords.x, coords.y, coords.z );
		}
		if( block == null || block.getTileEntity() == null ) {
			throw new IllegalStateException( "Can't assign central TileEntity for MBS." );
		}

		setCentralCoordinatesToTileEntities( chunk, coords.x, coords.y, coords.z );
	}

	protected WorldCoordinate getCentralCoordinate(WorldChunk chunk, int rotation) {
		WorldCoordinate coords = chunk.getBaseCoordinates();
		int centerX = width / 2;
		int centerY = -height / 2;
		int centerZ = depth / 2;
		return coords.translate( centerX, centerY, centerZ );
	}

	protected void setCentralCoordinatesToTileEntities(WorldChunk chunk, int x, int y, int z) {
		TileEntity tile;
		for( WorldBlock worldBlock : chunk ) {
			if( worldBlock != null ) {
				tile = worldBlock.getTileEntity();
				if( tile != null && tile instanceof TileStructure ) {
					((TileStructure) tile).setCentralCoordinates( x, y, z );
				}
			}
		}
	}


}
