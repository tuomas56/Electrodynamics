package electrodynamics.mbs.structure;

import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_BLADE;
import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_CASING;
import static electrodynamics.lib.block.StructureComponent.MOB_GRINDER_OUTPUT;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelMobGrinder;
import electrodynamics.client.model.ModelTechne;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.lib.client.Models;
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
	public ModelTechne getModel() {
		return model != null ? model : (model = new ModelMobGrinder());
	}

	@Override
	public String getModelTexture() {
		return Models.TEX_MOB_GRINDER;
	}

	@Override
	public void applyGLTransformations(TileStructure tile) {
		GL11.glTranslated(tile.xCoord + .5, tile.yCoord + 1.5, tile.zCoord + .5);
		GL11.glRotatef(180, 1, 0, 0);
		int rotation = tile.getRotation();
		GL11.glRotatef(90 * (rotation / 2), 0, 1, 0);
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
					
					if (key == 'c' && component == MOB_GRINDER_OUTPUT) {
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
				{ 'c', 'c', 'c' },
				{ 'c', 'c', 'c' },
				{ 'c', 'c', 'c' }
		} );

		compiler.addLayer( new char[][] { // y=1
				{ 'w', 'b', 'w' },
				{ 'w', 'b', 'w' },
				{ 'w', 'b', 'w' }
		} );


		Map<Character, StructureBlock> mappings = new HashMap<Character, StructureBlock>();
		mappings.put('c', matchAny(MOB_GRINDER_CASING, MOB_GRINDER_OUTPUT)); // casing
		mappings.put('w', matchAny(MOB_GRINDER_CASING));
		mappings.put('b', matchAny(MOB_GRINDER_BLADE)); // blade

		return compiler.compile( mappings );
	}

}
