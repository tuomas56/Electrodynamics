package electrodynamics.world;


import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import electrodynamics.block.BlockStructure;
import electrodynamics.mbs.MBSManager;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.mbs.WorldCoordinate;
import electrodynamics.mbs.util.WorldChunk;
import net.minecraft.world.IBlockAccess;

import java.util.EnumSet;
import java.util.LinkedList;

public class TickHandlerMBS implements ITickHandler {

	private static TickHandlerMBS instance;

	public static TickHandlerMBS instance() {
		if( instance == null )
			instance = new TickHandlerMBS();
		return instance;
	}

	private LinkedList<WorldCoordinate> queue = new LinkedList<WorldCoordinate>();
	private boolean queueEmpty = true;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if( queueEmpty )
			return;

		WorldCoordinate coords = queue.poll();
		queueEmpty = queue.isEmpty();

		WorldChunk chunk = getWorldChunk( coords );
		if( chunk != null ) {
			MultiBlockStructure mbs = MBSManager.findMatchFor( chunk );
			if( mbs != null )
				mbs.initialize( chunk );
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of( TickType.WORLD );
	}

	@Override
	public String getLabel() {
		return "ED-MBS";
	}

	public void scheduleTask(IBlockAccess world, int x, int y, int z) {
		queue.offer( new WorldCoordinate( world, x, y, z ) );
		queueEmpty = false;
	}

	private final int lowerX = 0;
	private final int upperX = 1;
	private final int lowerY = 2;
	private final int upperY = 3;
	private final int lowerZ = 4;
	private final int upperZ = 5;

	private WorldChunk getWorldChunk(WorldCoordinate coords) {
		int[] measures = new int[] { 0, 0, 0, 0, 0, 0 };
		int[] newMeasures;

		// get the measures by recursion
		while( !equalArrays( measures, newMeasures = getNewMeasures( coords, measures ) ) ) {
			measures = newMeasures;
		}

		WorldCoordinate start = coords.translate( -measures[lowerX], -measures[lowerY], -measures[lowerZ] );
		WorldCoordinate end = coords.translate( measures[upperX], measures[upperY], measures[upperZ] );

		return WorldChunk.getChunk( start, end );
	}

	private int[] getNewMeasures(WorldCoordinate coords, int[] measures) {
		int width = measures[lowerX] + measures[upperX] + 1;
		int height = measures[lowerY] + measures[upperY] + 1;
		int depth = measures[lowerZ] + measures[upperZ] + 1;

		// check X-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX] - 1, -measures[lowerY], -measures[lowerZ], 1, height, depth ) ) {
			measures[lowerX]++;
		}
		// check X-axis (upper)
		if( containsStructureBlock( coords, measures[upperX] + 1, -measures[lowerY], -measures[lowerZ], 1, height, depth ) ) {
			measures[upperX]++;
		}

		// check Y-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY] - 1, -measures[lowerZ], width, 1, depth ) ) {
			measures[lowerY]++;
		}
		// check Y-axis (upper)
		if( containsStructureBlock( coords, -measures[lowerX], measures[upperY] + 1, -measures[lowerZ], width, 1, depth ) ) {
			measures[upperY]++;
		}

		// check Z-axis (lower)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY], -measures[lowerZ] - 1, width, height, 1 ) ) {
			measures[lowerY]++;
		}
		// check Z-axis (upper)
		if( containsStructureBlock( coords, -measures[lowerX], -measures[lowerY], measures[upperZ] + 1, width, height, 1 ) ) {
			measures[upperY]++;
		}

		return measures;
	}

	private boolean equalArrays(int[] array1, int[] array2) {
		if( array1.length != array2.length )
			return false;
		for( int i = 0; i < array1.length; i++ ) {
			if( array1[i] != array2[i] ) {
				return false;
			}
		}
		return true;
	}

	private boolean containsStructureBlock(WorldCoordinate coords, int deltaX, int deltaY, int deltaZ, int width, int height, int depth) {
		for( int i = 0; i < width; i++ ) {
			int x = coords.x + deltaX + i;
			for( int j = 0; j < height; j++ ) {
				int y = coords.y + deltaY + j;
				for( int k = 0; k < depth; k++ ) {
					int z = coords.z + deltaZ + k;

					int id = coords.getBlockAccess().getBlockId( x, y, z );
					if( BlockStructure.isStrutureBlock( id ) )
						return true;
				}
			}
		}
		return false;
	}

}
