package electrodynamics.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.api.tool.ITool;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.tileentity.TileStructure;
import electrodynamics.world.TickHandlerMBS;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
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
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		scheduleUpdate(world, x, y, z, false);
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
				if( player.getHeldItem().itemID != EDItems.itemSledgeHammer.itemID ) {
					scheduleUpdate( world, x, y, z, true );
					if( !world.isRemote ) {
						player.sendChatToPlayer( tile.isValidStructure() ? "Valid structure!" : "Invalid structure." );
					}
				}
			}
			return tile.onBlockActivatedBy( player, side, xOff, yOff, zOff );
		}
		return false;
	}

	@Override
	public int getRenderType() {
		return ConfigurationSettings.STRUCTURE_BLOCK_RENDER_ID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side) {
		Icon icon = super.getBlockTexture( access, x, y, z, side );
		if( icon == null ) // If the texture is invalid, better paint the "standard" texture.
			return getIcon( StructureComponent.FURNACE_FRAME.ordinal(), side );
		return icon;
	}

	protected void scheduleUpdate(World world, int x, int y, int z, boolean doValidate) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z, doValidate );
	}

}
