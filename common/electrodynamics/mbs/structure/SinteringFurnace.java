package electrodynamics.mbs.structure;

import electrodynamics.client.model.ModelSinteringFurnace;
import electrodynamics.client.model.ModelTechne;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.lib.client.Models;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.mbs.Pattern;
import electrodynamics.mbs.StructureBlock;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileStructure;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import static electrodynamics.lib.block.StructureComponent.*;

public class SinteringFurnace extends MultiBlockStructure {

	public static final String UID = "SintFurnace";
	private static final int REQUIREMENT_HEATER = 1, REQUIREMENT_VALVE = 1, REQUIREMENT_GAUGE = 1, REQUIREMENT_VENT = 2;

	private ModelTechne model;

	public SinteringFurnace() {
		super( UID, makePattern() );
	}

	@Override
	public ModelTechne getModel() {
		return model != null ? model : (model = new ModelSinteringFurnace());
	}

	@Override
	public String getModelTexture() {
		return Models.TEX_SINT_FURNACE;
	}

	@Override
	public void applyGLTransformations(TileStructure tile) {
		GL11.glTranslated(tile.xCoord + .5, tile.yCoord + 1.5, tile.zCoord + .5);
		GL11.glRotatef(180, 1, 0, 0);
		int rotation = tile.getRotation();
		GL11.glRotatef(90 * (rotation / 2), 0, 1, 0);
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
				{ 'g', 'v', 'g' },
				{ 'w', 'w', 'w' },
				{ '-', '-', '-' }
		} );


		Map<Character, StructureBlock> mappings = new HashMap<Character, StructureBlock>();
		mappings.put( 'w', matchAny( FURNACE_FRAME ) ); // machine frame
		mappings.put( 'b', matchAny( CONVEYOR_BELT ) ); // conveyor belt
		mappings.put( 'x', matchAny( FURNACE_FRAME, FURNACE_HEATER, FURNACE_VALVE ) ); // heater, valve
		mappings.put( 'v', matchAny( FURNACE_FRAME, FURNACE_VENT ) ); // vent
		mappings.put( 'g', matchAny( FURNACE_FRAME, FURNACE_GAUGE ) ); // gauge

		return compiler.compile( mappings );
	}

}
