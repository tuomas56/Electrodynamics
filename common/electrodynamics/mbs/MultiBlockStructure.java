package electrodynamics.mbs;


import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.block.EDBlocks;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.mbs.util.WorldCoordinate;
import electrodynamics.network.packet.PacketInitializeMBS;
import electrodynamics.tileentity.TileStructure;

public abstract class MultiBlockStructure {

	// Unique string used to identity this multi-block during rendering
	public final String mbsID;

	private int width;
	private int height;
	private int depth;
	private Pattern pattern;

	private boolean isSymmetricXZ;

	public MultiBlockStructure(String mbsID, Pattern pattern) {
		this( mbsID, pattern, false );
	}

	public MultiBlockStructure(String mbsID, Pattern pattern, boolean isSymmetricXZ) {
		this.mbsID = mbsID;
		this.pattern = pattern;
		this.width = pattern.getWidth();
		this.height = pattern.getHeight();
		this.depth = pattern.getDepth();
		this.isSymmetricXZ = isSymmetricXZ;
	}

	public String getUID() {
		return mbsID;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public abstract ModelBase getModel();

	/**
	 * Checks if this MBS is found within the passed WorldChunk.
	 *
	 * @param chunk the chunk of blocks to be checked.
	 * @return an integer representing the rotation of the MBS, or -1 if not found.
	 */
	public int check(WorldChunk chunk) {
		//TODO Xhamolk_ Fix bug with certain MBS rotations
		
		// Compare dimensions:
		if( !compareDimensions( chunk ) )
			return -1;

		// Optimization Checks
		boolean rotated = !isSymmetricXZ && width != depth && width == chunk.getDepth();

		// Compare each block.
		boolean[] angles = new boolean[] { true, true };

		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				for( int z = 0; z < depth; z++ ) {
					WorldBlock worldBlock = rotated ? chunk.getBlockAt( z, y, x ) : chunk.getBlockAt( x, y, z );

					if( angles[0] && !getPattern().getBlockAt( x, y, z ).isMatchingBlock( worldBlock ) )
						angles[0] = false;

					if( !isSymmetricXZ ) {
						if( angles[1] && !getPattern().getBlockAt( width - x - 1, y, depth - z - 1 ).isMatchingBlock( worldBlock ) )
							angles[1] = false;
					}
				}
			}
		}

		if( isSymmetricXZ )
			return angles[0] ? 0 : -1;
		else {
			int retValue = angles[0] ? 0 : angles[1] ? 1 : -1;
			if( retValue != -1 && rotated ) {
				retValue += 2;
			}
			return retValue;
		}
	}

	protected boolean compareDimensions(WorldChunk chunk) {
		if( chunk.getHeight() != height )
			return false;

		return chunk.getWidth() == width && chunk.getDepth() == depth
				|| chunk.getWidth() == depth && chunk.getDepth() == width;
	}

	public void initialize(WorldChunk chunk, int rotation) {
		WorldCoordinate coords = getCentralCoordinate( chunk, rotation );

		validateTileEntities( chunk, rotation, coords.x, coords.y, coords.z );
		
		if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
			PacketInitializeMBS packet = new PacketInitializeMBS(chunk);
			PacketDispatcher.sendPacketToAllInDimension(packet.makePacket(), ((World)chunk.getBlockAccess()).provider.dimensionId);
		}
	}

	protected WorldCoordinate getCentralCoordinate(WorldChunk chunk, int rotation) {
		WorldCoordinate coords = new WorldCoordinate( chunk.getBlockAccess(), chunk.getBaseCoordinates().x, chunk.getBaseCoordinates().y, chunk.getBaseCoordinates().z );
		int centerX = chunk.getWidth() / 2;
		int centerZ = chunk.getDepth() / 2;
		return coords.translate( centerX, 0, centerZ );
	}

	protected void validateTileEntities(WorldChunk chunk, int rotation, int x, int y, int z) {
		TileEntity tile;
		TileStructure replacement = getNewCentralTileEntity();
		
		for( WorldBlock worldBlock : chunk ) {
			if( worldBlock != null ) {
				tile = worldBlock.getTileEntity();
				if( tile != null && tile instanceof TileStructure ) {
					((TileStructure) tile).validateStructure( this, rotation, x, y, z );

					if (((TileStructure)tile).isCentralTileEntity()) {
						if (replacement != null) {
							int subID = ((TileStructure)tile).getSubBlock();
							replacement.setSubBlock( subID );
							
							WorldCoordinate central = getCentralCoordinate(chunk, rotation);
							tile.worldObj.setBlockTileEntity(central.x, central.y, central.z, replacement);
							((TileStructure)worldBlock.getTileEntity()).validateStructure( this, rotation, x, y, z );
						}
					}
					
					((World)chunk.getBlockAccess()).markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
				}
			}
		}
		EDLogger.fine( String.format( "Validated Structure at: (%s, %s, %s)", x, y, z ) );
	}

	public StructureComponent getStructureComponentFrom(TileEntity tileEntity) {
		if( tileEntity != null && tileEntity instanceof TileStructure ) {
			TileStructure tile = (TileStructure) tileEntity;
			return StructureComponent.values()[tile.getSubBlock()];
		}
		return null;
	}
	
	public static StructureBlock matchAny(final StructureComponent... components) {
		return new StructureBlock() {
			@Override
			public boolean isMatchingBlock(WorldBlock worldBlock) {
				Block block = worldBlock.getBlock();
				if( block == null )
					return false;
				if( block.blockID == EDBlocks.blockStructureComponent.blockID ) {

					TileStructure tile = (TileStructure) worldBlock.getTileEntity();
					int subBlock = tile.getSubBlock();

					for( StructureComponent sub : components ) {
						if( subBlock == sub.ordinal() )
							return true;
					}
				}
				return false;
			}
		};
	}
	
	public TileStructure getNewCentralTileEntity() {
		return null;
	}
	
}
