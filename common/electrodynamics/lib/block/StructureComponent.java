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
		@Override
		public String[] getLocalTextureFiles() {
			String top = "sinteringFurnace_conveyorTop", side = "sinteringFurnace_conveyorSide";
			return new String[] { top, top, top, side, top, side };
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

	private static String frame = "sinteringFurnace_frame";
}
