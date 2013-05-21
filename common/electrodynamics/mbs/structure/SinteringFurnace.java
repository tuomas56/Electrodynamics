package electrodynamics.mbs.structure;


import electrodynamics.block.EDBlocks;
import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.mbs.Pattern;
import electrodynamics.mbs.StructureBlock;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;

import java.util.HashMap;
import java.util.Map;

import static electrodynamics.lib.block.StructureComponent.*;

public class SinteringFurnace extends MultiBlockStructure {

	public static final String UID = "SintFurnace";
	private static final int REQUIREMENT_HEATER = 1, REQUIREMENT_VALVE = 1, REQUIREMENT_GAUGE = 1, REQUIREMENT_VENT = 2;

	private ModelSinteringFurnace model;

	public SinteringFurnace() {
		super( UID, makePattern() );
	}

	@Override
	public ModelBase getModel() {
		return model != null ? model : (model = new ModelSinteringFurnace());
	}

	@Override
	public int check(WorldChunk chunk) {
		// use the c value to rotate the pattern,
		// and then check if the MBS really has all of the components.

		int c = super.check( chunk );
		if( c == -1 ) {
			return -1; // invalid MBS
		}

		Pattern pattern = getPattern();
		int width = pattern.getWidth();
		int height = pattern.getHeight();
		int depth = pattern.getDepth();
		int heaterCount = 0, gaugeCount = 0, valveCount = 0, ventCount = 0;

		boolean rotated = c > 1, reversed = c % 2 == 1; // optimization checks.

		for( int x = 0; x < width; x++ ) {
			for( int y = 0; y < height; y++ ) {
				for( int z = 0; z < depth; z++ ) {
					char key = reversed ? pattern.getKeyAt( width - x - 1, y, depth - z - 1 ) : pattern.getKeyAt( x, y, z );
					if( key == 'w' )
						continue;

					WorldBlock worldBlock = rotated ? chunk.getBlockAt( z, y, x ) : chunk.getBlockAt( x, y, z );
					StructureComponent component = getStructureComponentFrom( worldBlock.getTileEntity() );
					if( key == 'x' ) {
						if( component == FURNACE_HEATER )
							heaterCount++;
						else if( component == FURNACE_VALVE )
							valveCount++;
					} else if( key == 'g' ) {
						if( component == FURNACE_GAUGE )
							gaugeCount++;
					} else if( key == 'v' ) {
						if( component == FURNACE_VENT )
							ventCount++;
					}
				}
			}
		}

		// Check the component requirement.
		if( heaterCount >= REQUIREMENT_HEATER && gaugeCount >= REQUIREMENT_GAUGE && valveCount >= REQUIREMENT_VALVE && ventCount >= REQUIREMENT_VENT ) {
			return c;
		}
		return -1;
	}

	private StructureComponent getStructureComponentFrom(TileEntity tileEntity) {
		if( tileEntity != null && tileEntity instanceof TileStructure ) {
			TileStructure tile = (TileStructure) tileEntity;
			return StructureComponent.values()[tile.getSubBlock()];
		}
		return null;
	}

	private static Pattern makePattern() {
		Pattern.PatternCompiler compiler = new Pattern.PatternCompiler( 7, 3 );

		compiler.addLayer( new char[][] { // y=0
				{ 'w', 'b', 'w' },
				{ 'w', 'b', 'w' },
				{ 'x', 'b', 'x' },
				{ 'x', 'b', 'x' },
				{ 'x', 'b', 'x' },
				{ 'w', 'b', 'w' },
				{ 'w', 'b', 'w' }
		} );

		compiler.addLayer( new char[][] { // y=1
				{ '-', '-', '-' },
				{ 'w', 'w', 'w' },
				{ 'g', 'v', 'g' },
				{ 'g', 'v', 'g' },
				{ 'w', 'w', 'w' },
				{ 'w', 'w', 'w' },
				{ '-', '-', '-' }
		} );


		Map<Character, StructureBlock> mappings = new HashMap<Character, StructureBlock>();
		mappings.put( 'w', matchAny( FURNACE_FRAME ) ); // machine frame
		mappings.put( 'b', matchAny( CONVEYOR_BELT ) ); // conveyor belt
		mappings.put( 'x', matchAny( FURNACE_FRAME, FURNACE_HEATER, FURNACE_VALVE ) ); // heater, valve
		mappings.put( 'v', matchAny( FURNACE_VENT ) ); // vent
		mappings.put( 'g', matchAny( FURNACE_FRAME, FURNACE_GAUGE ) ); // gauge

		return compiler.compile( mappings );
	}


	private static StructureBlock matchAny(final StructureComponent... components) {
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


}
