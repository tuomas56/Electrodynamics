package electrodynamics.mbs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.core.EDLogger;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.network.packet.PacketInvalidateMBS;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
		boolean notifyClient = false;
		for( WorldBlock block : chunk ) {
			if( block != null ) {
				TileEntity tile = block.getTileEntity();
				if( tile != null && tile instanceof TileStructure ) {
					TileStructure structure = (TileStructure) tile;
					notifyClient = notifyClient || structure.isValidStructure();
					structure.invalidateStructure();
					((World)chunk.getBlockAccess()).markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
				}
			}
		}
		if( notifyClient && FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketInvalidateMBS packet = new PacketInvalidateMBS(chunk);
			PacketDispatcher.sendPacketToAllInDimension( packet.makePacket(), ((World) chunk.getBlockAccess()).provider.dimensionId );
		}
	}

}
