package electrodynamics.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.api.tool.ITool;
import electrodynamics.api.tool.ToolType;
import electrodynamics.client.render.block.RenderBlockStructure;
import electrodynamics.core.CreativeTabED;
import electrodynamics.interfaces.IAcceptsTool;
import electrodynamics.item.EDItems;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.tileentity.TileEntityMobGrinder;
import electrodynamics.tileentity.TileStructure;
import electrodynamics.world.TickHandlerMBS;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Set;

/**
 * Blocks used for MBS
 */
public class BlockStructure extends BlockGeneric implements IAcceptsTool {

	public BlockStructure(int blockID) {
		super( blockID, Material.iron );
		setHardness( 3.0F );
		setCreativeTab( CreativeTabED.block );
	}

	//TODO Move this somewhere better
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		TileStructure tile = (TileStructure) par1World.getBlockTileEntity(par2, par3, par4);
		if( tile != null ) {
			if (StructureComponent.values()[tile.getSubBlock()] == StructureComponent.MOB_GRINDER_BLADE && tile.isValidStructure()) {
				return null;
			}
		}
		
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}
	
	@Override
	protected Set<? extends SubBlock> getSubBlocks() {
		return EnumSet.allOf( StructureComponent.class );
	}

	@Override
	public TileEntity createSpecificTileEntity(World world, int x, int y, int z, NBTTagCompound nbt, int subBlock){
		if( subBlock == StructureComponent.MACHINE_FRAME.ordinal() ) {
			if (nbt.hasKey("mbsID") && nbt.getString("mbsID").equals("MobGrinder")) {
				int targetX = nbt.getInteger( "targetX" );
				int targetY = nbt.getInteger( "targetY" );
				int targetZ = nbt.getInteger( "targetZ" );

				if( x == targetX && y == targetY && z == targetZ ) { // is central TE
					return new TileEntityMobGrinder();
				}
			}
		}
		return super.createSpecificTileEntity( world, x, y, z, nbt, subBlock );
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
				}
			}
			return tile.onBlockActivatedBy( player, side, xOff, yOff, zOff );
		}
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderBlockStructure.renderID;
	}

	public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }
	
	@Override
	public int getLightOpacity(World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity( x, y, z );
		if( tile != null && tile instanceof TileStructure && (((TileStructure) tile).isValidStructure() || (StructureComponent.values()[((TileStructure)tile).getSubBlock()].getModel() != null)))
			return 0;
		return 255;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z, int side) {
		Icon icon = super.getBlockTexture( access, x, y, z, side );
		if( icon == null ) // If the texture is invalid, better paint the "standard" texture.
			return getIcon( StructureComponent.MACHINE_FRAME.ordinal(), side );
		return icon;
	}

	protected void scheduleUpdate(World world, int x, int y, int z, boolean doValidate) {
		TickHandlerMBS.instance().scheduleTask( world, x, y, z, doValidate );
	}

	public int idPicked(World world, int x, int y, int z) {
		return this.blockID;
	}

	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean accepts(ToolType tool) {
		return tool == ToolType.HAMMER;
	}

	@Override
	public boolean onToolUse(World world, int x, int y, int z, EntityPlayer player, ItemStack stack) {
		scheduleUpdate(world, x, y, z, true);
		return true;
	}
	
}
