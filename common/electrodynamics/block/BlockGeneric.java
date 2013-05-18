package electrodynamics.block;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.tileentity.TileEntityGeneric;
import electrodynamics.util.BlockUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.List;
import java.util.Random;
import java.util.Set;


public abstract class BlockGeneric extends BlockContainer {

	public BlockGeneric(int blockID, Material material) {
		super( blockID, material );
	}

	/**
	 * A Set containing all the <code>SubBlock</code> objects for this block.
	 * <p/>
	 * Recommended use:
	 * Create an enum that implements <code>SubBlock</code>.
	 * Override this method with <code>EnumSet.allOf(yourEnum.class)</code>
	 */
	protected abstract Set<? extends SubBlock> getSubBlocks();

	/**
	 * Gets the sub-blocks as an array of <code>SubBlock</code>.
	 */
	public SubBlock[] getSubBlocksArray() {
		Set<? extends SubBlock> subBlocks = getSubBlocks();
		return subBlocks.toArray( new SubBlock[subBlocks.size()] );
	}

	/**
	 * Gets the sub-block index from an ItemStack's damage value.
	 */
	public int getSubBlockIndex(ItemStack itemStack) {
		return itemStack.getItemDamage();
	}

	@Override
	public final TileEntity createNewTileEntity(World world) {
		return TileEntityGeneric.createReplaceableTE();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack itemStack) {
		// set the front side to the metadata
		ForgeDirection front = BlockUtil.getSideFacingEntity( living, x, z );
		int metadata = front.ordinal();
		world.setBlockMetadataWithNotify( x, y, z, metadata, 3 );

		// determine sub-block
		int subBlock = getSubBlockIndex( itemStack );

		// create and set TileEntity
		TileEntityGeneric tile = getSubBlocksArray()[subBlock].createNewTileEntity( world );
		world.setBlockTileEntity( x, y, z, tile );

		// set the sub block to the tile entity
		tile.setSubBlock( subBlock );
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		// Register all the sub-blocks.
		for( SubBlock subBlock : getSubBlocks() ) {
			list.add( subBlock.toItemStack() );
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
		TileEntityGeneric tile = (TileEntityGeneric) world.getBlockTileEntity( x, y, z );
		if( tile != null ) { // drop the block
			int sub = tile.getSubBlock();
			SubBlock subBlock = getSubBlocksArray()[sub];
			this.dropBlockAsItem_do( world, x, y, z, subBlock.toItemStack() );
		}
		super.breakBlock( world, x, y, z, blockID, metadata ); // break block and remove TE.
	}

	@Override
	public int quantityDropped(Random random) {
		return 0; // let breakBlock handle dropping the item(s)
	}

	// Textures

	@SideOnly(Side.CLIENT)
	Icon[][] icons;

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		// Used only for rendering the blocks in the inventory.
		return icons[metadata][side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side) {
		TileEntityGeneric tile = (TileEntityGeneric) access.getBlockTileEntity( x, y, z );
		if( tile == null )
			return null; // prevent null pointers; although this shouldn't be needed.

		int subBlock = tile.getSubBlock();
		int front = access.getBlockMetadata( x, y, z );

		// Assuming that no component will ever face up or down.
		if( side == 0 || side == 1 ) {
			return getIcon( side, subBlock );
		}

		// iterate through the orientations.
		ForgeDirection currentSide = ForgeDirection.getOrientation( front );
		for( int i = 2; i < 6; i++ ) {
			if( currentSide.ordinal() == side )
				return getIcon( i, subBlock );
			currentSide = currentSide.getRotation( ForgeDirection.DOWN ); // rotate to the right.
		}

		return null; // Texture not found?
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		SubBlock[] subBlocks = getSubBlocksArray();
		icons = new Icon[subBlocks.length][6];

		int i = 0;
		for( SubBlock subBlock : subBlocks ) {
			String[] textures = subBlock.getTextureFiles();
			for( int e = 0; e < textures.length; e++ ) {
				if( textures[e] != null ) {
					icons[i][e] = register.registerIcon( textures[e] );
				}
			}
			i++;
		}
	}

}
