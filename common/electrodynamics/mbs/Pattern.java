package electrodynamics.mbs;


import electrodynamics.mbs.util.StructureBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pattern {

	private int width;
	private int height;
	private int depth;
	private char[][][] pattern;
	private Map<Character, StructureBlock> mappings;

	private Pattern(int width, int height, int depth, char[][][] pattern, Map<Character, StructureBlock> mappings) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.pattern = pattern;
		this.mappings = mappings;
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

	/**
	 * Gets the block representation used to check StructureBlock.isMatchingBlock(WorldBlock).
	 * Uses relative positions to the structure's origin (0,0,0).
	 *
	 * @param x the relative x position.
	 * @param y the relative y position.
	 * @param z the relative z position.
	 */
	public StructureBlock getBlockAt(int x, int y, int z) {
		char key = pattern[y][x][z];
		if( key == ' ' ) {
			return StructureBlock.BLOCK_ANY;
		}
		if( key == '-' ) {
			return StructureBlock.BLOCK_AIR;
		}
		return mappings.get( key );
	}

	public static class PatternCompiler {

		private int width;  // x
		private int depth;  // z
		private List<char[][]> layers = new ArrayList<char[][]>();

		private List<Character> keys = new ArrayList<Character>();

		public PatternCompiler(int width, int depth) {
			this.width = width;
			this.depth = depth;
		}

		public void addLayer(char[][] layer) {
			for( int x = 0; x < width; x++ ) {
				for( int z = 0; z < depth; z++ ) {
					char key = layer[x][z];
					addKey( key );
				}
			}
			layers.add( layer );
		}

		public List<Character> getKeys() {
			return keys;
		}

		public Pattern compile(Map<Character, StructureBlock> mappings) {
			if( !checkMappings( mappings ) ) {
				throw new IllegalArgumentException( "Wrong mappings for this pattern." );
			}
			int height = layers.size();
			char[][][] pattern = (char[][][]) layers.toArray();
			return new Pattern( width, height, depth, pattern, mappings );
		}

		private void addKey(char key) {
			if( !keys.contains( key ) )
				keys.add( key );
		}

		// must make sure that there is a value for every registered key.
		private boolean checkMappings(Map<Character, StructureBlock> mappings) {
			for( char key : keys ) {
				if( !mappings.containsKey( key ) || mappings.get( key ) == null )
					return false;
			}
			return true;
		}

	}

}
