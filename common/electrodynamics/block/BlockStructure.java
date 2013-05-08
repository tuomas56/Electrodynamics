package electrodynamics.block;

import electrodynamics.tileentity.TileStructure;
import electrodynamics.world.TickHandlerMBS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Blocks used for MBS
 */
public abstract class BlockStructure extends BlockContainer {

	public BlockStructure(int blockID, Material material) {
		super( blockID, material );
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		TileStructure tile = (TileStructure) world.getBlockTileEntity( x, y, z );
		if( tile != null ) {
			return tile.onBlockActivatedBy( player, side, xOff, yOff, zOff );
		}
		return false;
	}

	@Override
	public abstract TileStructure createNewTileEntity(World world);

	protected void scheduleUpdate(World world, int x, int y, int z) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z );
	}

}
