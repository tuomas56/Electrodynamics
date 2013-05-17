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
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		Block block = Block.blocksList[neighborID];
		if( block != null && block instanceof BlockStructure ) {
			scheduleUpdate( world, x, y, z, false );
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		TileStructure tile = (TileStructure) world.getBlockTileEntity( x, y, z );
		if( tile != null ) {
			if( player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ITool ) {
				player.sendChatToPlayer( tile.isValidStructure() ? "Valid structure!" : "Invalid structure." );
				scheduleUpdate( world, x, y, z, true );
			}
			return tile.onBlockActivatedBy( player, side, xOff, yOff, zOff );
		}
		return false;
	}

	protected void scheduleUpdate(World world, int x, int y, int z, boolean doValidate) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z, doValidate );
	}

}
