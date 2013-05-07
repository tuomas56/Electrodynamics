package electrodynamics.mbs;

import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.tileentity.TileEntity;

import java.util.LinkedList;
import java.util.List;

public class MBSManager {

	private static List<MultiBlockStructure> structures = new LinkedList<MultiBlockStructure>();

	public static void registerMBS(MultiBlockStructure mbs) {
		structures.add( mbs );
	}

	public static void updateStructuresAt(WorldChunk chunk) {
		for( MultiBlockStructure mbs : structures ) {
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
