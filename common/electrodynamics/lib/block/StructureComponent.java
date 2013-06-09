package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.block.EDBlocks;
import electrodynamics.block.SubBlock;
import electrodynamics.client.model.ModelChicken;
import electrodynamics.client.model.ModelTechne;
import electrodynamics.lib.client.Models;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityGeneric;
import electrodynamics.tileentity.TileStructure;

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

		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	FURNACE_FRAME( Strings.STRUCTURE_COMPONENT_FURNACE_FRAME ) {
		@Override
		public String[] getLocalTextureFiles() {
			return new String[] { frame, frame, frame, frame, frame, frame };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	FURNACE_HEATER( Strings.STRUCTURE_COMPONENT_FURNACE_HEATER ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_heaterBlock";
			return new String[] { base, base, base, base, base, base };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	FURNACE_GAUGE( Strings.STRUCTURE_COMPONENT_FURNACE_GAUGE ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_gauge";
			return new String[] { frame, frame, base, frame, base, frame };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	FURNACE_VALVE( Strings.STRUCTURE_COMPONENT_FURNACE_VALVE ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_valve";
			return new String[] { frame, frame, base, frame, base, frame };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	FURNACE_VENT( Strings.STRUCTURE_COMPONENT_FURNACE_VENT ) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "sinteringFurnace_vent";
			return new String[] { base, base, base, base, base, base };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	
	MOB_GRINDER_CASING(Strings.STRUCTURE_COMPONENT_MOB_GRINDER_CASING) {
		@Override
		String[] getLocalTextureFiles() {
			return new String[] {casing, casing, casing, casing, casing, casing};
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	
	MOB_GRINDER_OUTPUT(Strings.STRUCTURE_COMPONENT_MOB_GRINDER_OUTPUT) {
		@Override
		public String[] getLocalTextureFiles() {
			String base = "mobGrinder_valve";
			return new String[] { casing, casing, base, casing, base, casing };
		}
		
		@Override
		public ModelTechne getModel() {
			return null;
		}

		@Override
		public String getModelTexture() {
			return null;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
		}
	},
	
	MOB_GRINDER_BLADE(Strings.STRUCTURE_COMPONENT_MOB_GRINDER_BLADE) {
		@Override
		String[] getLocalTextureFiles() {
			return null;
		}
		
		@Override
		public ModelTechne getModel() {
			return new ModelChicken();
		}

		@Override
		public String getModelTexture() {
			return Models.TEX_CHICKEN;
		}

		@Override
		public void applyGLTransformations(byte renderType) {
			
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
		if (local != null) {
			String[] textures = new String[local.length];
			for( int i = 0; i < local.length; i++ ) {
				textures[i] = ModInfo.ICON_PREFIX + "machine/mbs/" + local[i];
			}
			return textures;
		}
		return new String[0];
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

	/* MODEL RENDERING */
	/**
	 * The model to render. Prevents normal block + textures from rendering if not null
	 * @return An instance of ModelTechne
	 */
	public abstract ModelTechne getModel();	
	/**
	 * Texture to be applied to rendered model
	 * @return Complete path to model texture, starting with /mods/electrodynamics/
	 */
	public abstract String getModelTexture();	
	/**
	 * Any GL transformations that should be applied upon rendering.
	 * @param renderType 0 or 1 value indicating render type. <br />
	 * <b>0: </b> Rendering in world
	 * <b>1: </b> Rendering in inventory/hand/as entity
	 */
	public abstract void applyGLTransformations(byte renderType);
	
	private static String frame = "sinteringFurnace_frame";
	private static String casing = "mobGrinder_casing";
}
