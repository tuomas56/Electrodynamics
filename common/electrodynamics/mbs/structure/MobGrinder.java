package electrodynamics.mbs.structure;

import java.util.HashMap;
import java.util.Map;

import electrodynamics.lib.block.StructureComponent;
import electrodynamics.mbs.MultiBlockStructure;
import electrodynamics.mbs.Pattern;
import electrodynamics.mbs.StructureBlock;
import electrodynamics.mbs.util.WorldBlock;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.tileentity.structure.TileEntityMobGrinder;
import electrodynamics.tileentity.structure.TileEntityStructure;

public class MobGrinder extends MultiBlockStructure {

	public static final String UID = "MobGrinder";

	public static final int VALVE_COUNT = 1;
	public static final int HATCH_COUNT = 1;
	
	public MobGrinder() {
		super(UID, makePattern());
	}

	@Override
	public TileEntityStructure getNewCentralTileEntity() {
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

		int valveCount = 0;
		int hatchCount = 0;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < depth; z++) {
					char key = reversed ? pattern.getKeyAt(width - x - 1, y, depth - z - 1) : pattern.getKeyAt(x, y, z);

					WorldBlock worldBlock = rotated ? chunk.getBlockAt(z, y, x) : chunk.getBlockAt(x, y, z);
					StructureComponent component = getStructureComponentFrom(worldBlock.getTileEntity());
					
					if (key == 'o') {
						if (component == StructureComponent.VALVE) {
							valveCount++;
						} else if (component == StructureComponent.HATCH) {
							hatchCount++;
						}
					}
				}
			}
		}

		if (valveCount == VALVE_COUNT && hatchCount == HATCH_COUNT) {
			return c;
		}
		
		return -1;
	}

	private static Pattern makePattern() {
		Pattern.PatternCompiler compiler = new Pattern.PatternCompiler( 3, 3 );

		compiler.addLayer( new char[][] { // y=0
				{ 'o', 'c', 'o' },
				{ 'c', 'c', 'c' },
				{ 'c', 'c', 'c' }
		} );

		compiler.addLayer( new char[][] { // y=1
				{ 'c', '-', 'c' },
				{ 'c', 'b', 'c' },
				{ 'c', '-', 'c' }
		} );


		Map<Character, StructureBlock> mappings = new HashMap<Character, StructureBlock>();
		mappings.put('c', matchAny(StructureComponent.MACHINE_FRAME)); // casing
		mappings.put('b', matchAny(StructureComponent.MOB_GRINDER_BLADE)); // blade
		mappings.put('o', matchAny(StructureComponent.VALVE, StructureComponent.HATCH)); // output
		
		return compiler.compile( mappings );
	}

}
