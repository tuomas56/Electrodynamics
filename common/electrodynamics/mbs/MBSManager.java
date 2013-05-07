package electrodynamics.mbs;

import electrodynamics.mbs.util.WorldChunk;

import java.util.LinkedList;
import java.util.List;

public class MBSManager {

	private static List<MultiBlockStructure> structures = new LinkedList<MultiBlockStructure>();

	public static void registerMBS(MultiBlockStructure mbs) {
		structures.add( mbs );
	}

	public static MultiBlockStructure findMatchFor(WorldChunk chunk) {
		for( MultiBlockStructure mbs : structures ) {
			if( mbs.check( chunk ) != -1 )
				return mbs;
		}
		return null;
	}

}
