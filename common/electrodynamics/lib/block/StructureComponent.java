package electrodynamics.lib.block;


import electrodynamics.block.EDBlocks;
import electrodynamics.block.SubBlock;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityGeneric;
import electrodynamics.tileentity.TileStructure;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public enum StructureComponent implements SubBlock {

	CONVEYOR_BELT( Strings.STRUCTURE_COMPONENT_CONVEYOR_BELT ) {
		int[][] rotations = new int[][] {
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 3, 0, 3, 0, 1, 2 },
				{ 0, 3, 0, 3, 2, 0 }, // last pair is wrong.
				{ 2, 2, 2, 1, 3, 0 },
				{ 1, 1, 2, 1, 0, 3 }, // middle pair is wrong.
		};

		@Override
		public String[] getLocalTextureFiles() {
			String top = "sinteringFurnace_conveyorTop", side = "sinteringFurnace_conveyorSide";
			return new String[] { top, top, top, side, top, side };
		}

		@Override
		public int[][] getRotationMatrix() {
			return rotations;
		}
	},
	FURNACE_FRAME( Strings.STRUCTURE_COMPONENT_FURNACE_FRAME ) {
		@Override
		public String[] getLocalTextureFiles() {
			return new String[] { frame, frame, frame, frame, frame, frame };
		}
	},
	FURNACE_HEATER( Strings.STRUCTURE_COMPONENT_FURNACE_HEATER ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_heaterBlock";
			return new String[] { base, base, base, base, base, base };
		}
	},
	FURNACE_GAUGE( Strings.STRUCTURE_COMPONENT_FURNACE_GAUGE ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_gauge";
			return new String[] { frame, frame, base, frame, base, frame };
		}
	},
	FURNACE_VALVE( Strings.STRUCTURE_COMPONENT_FURNACE_VALVE ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_valve";
			return new String[] { frame, frame, base, frame, base, frame };
		}
	},
	FURNACE_VENT( Strings.STRUCTURE_COMPONENT_FURNACE_VENT ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_vent";
			return new String[] { base, base, base, base, base, base };
		}
	};


	private final String unLocalizedName;

	private StructureComponent(String unLocalizedName) {
		this.unLocalizedName = unLocalizedName;
	}

	@Override
	public String getUnlocalizedName() {
		return unLocalizedName;
	}

	abstract String[] getLocalTextureFiles();

	@Override
	public String[] getTextureFiles() {
		String[] local = getLocalTextureFiles();
		String[] textures = new String[local.length];
		for( int i = 0; i < local.length; i++ ) {
			textures[i] = ModInfo.ICON_PREFIX + "machine/mbs/" + local[i];
		}
		return textures;
	}

	@Override
	public TileEntityGeneric createNewTileEntity(World world) {
		return TileStructure.createNewPlaceHolderTE();
	}

	@Override
	public ItemStack toItemStack() {
		return new ItemStack( EDBlocks.blockStructureComponent, 1, ordinal() );
	}

	/**
	 * Gets the matrix that describes the texture rotations to be used for this component.
	 * The order of the values matches the order of <code>ForgeDirection</code> enum constants.
	 *
	 * By default, an array of 6x0 is returned; which indicates that no rotation is necessary.
	 * @return A 6x6 array of int.
	 */
	public int[][] getRotationMatrix() {
		return new int[6][0];
	}

	private static String frame = "sinteringFurnace_frame";
}
