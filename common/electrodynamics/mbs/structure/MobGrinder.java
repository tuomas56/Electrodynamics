package electrodynamics.mbs.structure;

import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_BLADE;
import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_CASING;
import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_OUTPUT;

import java.util.HashMap;
import java.util.Map;

import electrodynamics.lib.block.StructureComponent;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.mbs.Pattern;
import electrodynamics.mbs.StructureBlock;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.TileEntityMobGrinder;
import electrodynamics.tileentity.TileStructure;

public class MobGrinder extends MultiBlockStructure {

	public static final String UID = "MobGrinder";

	public static final int VALVE_COUNT = 1;
	
	public MobGrinder() {
		super(UID, makePattern());
	}

	@Override
	public TileStructure getNewCentralTileEntity() {
		return new TileEntityMobGrinder();
	}
	
	@Override
	public int check(WorldChunk chunk) {
		// use the c value to rotate the pattern,
		// and then check if the MBS really has all of the components.

		int c = super.check(chunk);
		if (c == -1) {
			return -1; // invalid MBS
		}

		Pattern pattern = getPattern();
		int width = pattern.getWidth();
		int height = pattern.getHeight();
		int depth = pattern.getDepth();

		boolean rotated = c > 1, reversed = c % 2 == 1; // optimization checks.

		int outputCount = 0;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					char key = reversed ? pattern.getKeyAt(width - x - 1, y, depth - z - 1) : pattern.getKeyAt(x, y, z);

					WorldBlock worldBlock = rotated ? chunk.getBlockAt(z, y, x) : chunk.getBlockAt(x, y, z);
					StructureComponent component = getStructureComponentFrom(worldBlock.getTileEntity());
					
					if (key == 'o' && component == MOB_GRINDER_OUTPUT) {
						outputCount++;
					}
				}
			}
		}

		if (outputCount == 1) {
			return c;
		}
		
		return -1;
	}

	private static Pattern makePattern() {
		Pattern.PatternCompiler compiler = new Pattern.PatternCompiler( 3, 3 );

		compiler.addLayer( new char[][] { // y=0
				{ 'o', 'c', 'c' },
				{ 'c', 'c', 'c' },
				{ 'c', 'c', 'c' }
		} );

		compiler.addLayer( new char[][] { // y=1
				{ 'c', 'b', 'c' },
				{ 'c', 'b', 'c' },
				{ 'c', 'b', 'c' }
		} );


		Map<Character, StructureBlock> mappings = new HashMap<Character, StructureBlock>();
		mappings.put('c', matchAny(MOB_GRINDER_CASING)); // casing
		mappings.put('b', matchAny(MOB_GRINDER_BLADE)); // blade
		mappings.put('o', matchAny(MOB_GRINDER_OUTPUT)); // output
		
		return compiler.compile( mappings );
	}

}
