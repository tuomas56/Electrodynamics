package electrodynamics.world.mbs;


import electrodynamics.world.mbs.util.WorldChunk;

public abstract class MultiBlockStructure {

	private int width;
	private int height;
	private int depth;
	private Pattern pattern;

	public MultiBlockStructure(Pattern pattern) {
		this.pattern = pattern;
		this.width = pattern.getWidth();
		this.height = pattern.getHeight();
		this.depth = pattern.getDepth();
	}

	public Pattern getPattern() {
		return pattern;
	}

	public boolean check(WorldChunk chunk) {

		// Compare dimensions:
		if( !compareDimensions( chunk ) )
			return false;

		// todo: algorithm to rotate in the x-z plane.

		// Compare each block.
		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				for( int z = 0; z < depth; z++ ) {
					if( !pattern.getBlockAt( x, y, z ).isMatchingBlock( chunk.getBlockAt( x, y, z ) ) )
						return false;
				}
			}
		}

		return true;
	}

	protected boolean compareDimensions(WorldChunk chunk) {
		if( chunk.getHeight() != height )
			return false;

		return chunk.getWidth() == width && chunk.getDepth() == depth
				|| chunk.getWidth() == depth && chunk.getDepth() == width;
	}

	public void initialize(WorldChunk chunk) {
		// todo - implementation.
	}


}
