package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.machine.utilty.TileEntityBlockDeployer;

public enum UtilityBlock {

	BLOCK_DEPLOYER(Strings.BLOCK_UTILITY) {
		
		@Override
		public String[] getTextures() {
			return new String[] {"null", "null", "null"};
		}

		@Override
		public TileEntity getTileEntity() {
			return new TileEntityBlockDeployer();
		}
		
	};
	
	public String unlocalizedName;
	
	private UtilityBlock(String unlocName) {
		this.unlocalizedName = unlocName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_UTILITY_ID, 1, this.ordinal());
	}
	
	/**
	 * Texture names for utility block
	 * @return Returns a 3-length String array of texture paths. <br />
	 * Order is FRONT, BACK, SIDES
	 */
	public abstract String[] getTextures();
	
	/**
	 * Tile Entity to be created on block placement
	 * @return Instance of desired TileEntity
	 */
	public abstract TileEntity getTileEntity();
	
}
