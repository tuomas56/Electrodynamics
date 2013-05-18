package electrodynamics.mbs;

import electrodynamics.core.EDLogger;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.tileentity.TileEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MBSManager {

	private static Map<String, MultiBlockStructure> structures = new HashMap<String, MultiBlockStructure>();

	public static void registerMBS(MultiBlockStructure mbs) {
		if( mbs != null )
			structures.put( mbs.getUID(), mbs );
		else
			EDLogger.warn( "Attempting to register a null mbs." );
	}

	public static Set<String> getRegisteredMBS() {
		return structures.keySet();
	}

	public static MultiBlockStructure getMultiBlockStructure(String mbsID) {
		return structures.get( mbsID );
	}

	public static void updateStructuresAt(WorldChunk chunk) {
		for( MultiBlockStructure mbs : structures.values() ) {
			int check = mbs.check( chunk );
			if( check != -1 ) {
				mbs.initialize( chunk, check );
				return;
			}
		}
		invalidateChunk( chunk );
	}

	public static void invalidateChunk(WorldChunk chunk) {
		for( WorldBlock block : chunk ) {
			if( block != null ) {
				TileEntity tile = block.getTileEntity();
				if( tile != null && tile instanceof TileStructure ) {
					((TileStructure) tile).invalidateStructure();
				}
			}
		}
	}

}
