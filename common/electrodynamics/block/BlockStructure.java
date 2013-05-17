package electrodynamics.block;

import electrodynamics.api.tool.ITool;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.tileentity.TileStructure;
import electrodynamics.world.TickHandlerMBS;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

/**
 * Blocks used for MBS
 */
public class BlockStructure extends BlockGeneric {

	public BlockStructure(int blockID) {
		super( blockID, Material.iron );
		setHardness( 3.0F );
		setCreativeTab( CreativeTabED.block );
	}

	@Override
	protected Set<? extends SubBlock> getSubBlocks() {
		return EnumSet.allOf( StructureComponent.class );
	}

	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata) {
		scheduleUpdate( world, x, y, z );
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		Block block = Block.blocksList[neighborID];
		if( block != null && block instanceof BlockStructure ) {
			scheduleUpdate( world, x, y, z );
		}
	}

	@Override
	public int quantityDropped(Random random) {
		return 0; // let breakBlock handle dropping the item(s)
	}

	public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
		TileStructure tile = (TileStructure) world.getBlockTileEntity( x, y, z );
		if( tile != null ) { // drop the block
			int sub = tile.getSubBlock();
			this.dropBlockAsItem_do( world, x, y, z, StructureComponent.values()[sub].toItemStack() );
		}
		super.breakBlock( world, x, y, z, blockID, metadata ); // break block and remove TE.
	}


	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		TileStructure tile = (TileStructure) world.getBlockTileEntity( x, y, z );
		if( tile != null ) {
			if( player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ITool ) {
				System.out.println( tile.isValidStructure() ? "Valid structure! =)" : "Invalid structure =(" );
			}
			return tile.onBlockActivatedBy( player, side, xOff, yOff, zOff );
		}
		return false;
	}

	protected void scheduleUpdate(World world, int x, int y, int z) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z );
	}

}
