package electrodynamics.block;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.tileentity.TileEntityGeneric;

public interface SubBlock {

	/**
	 * Get the un-localized name for this sub-block.
	 */
	String getUnlocalizedName();

	/**
	 * Get the texture files for registering the icons.
	 * <p/>
	 * The array must contain the files for
	 * The files Register the textures in the following order: bottom, top, front, right, back, left.
	 *
	 * @return an array of String of length 6.
	 */
	String[] getTextureFiles();

	/**
	 * Create a new TileEntityGeneric for this sub-block.
	 * Can NOT return null.
	 *
	 * @param world the world in which the TileEntityGeneric will be placed.
	 */
	TileEntityGeneric createNewTileEntity(World world);

	ItemStack toItemStack();

}
