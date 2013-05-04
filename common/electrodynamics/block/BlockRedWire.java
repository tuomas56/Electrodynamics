package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityRedWire;

public class BlockRedWire extends BlockContainer {

	/** Do wire blocks provide power. Set to false during updates to avoid looping */
	public static boolean wiresProvidePower = true;
	
	public BlockRedWire(int id) {
		super(id, Material.circuits);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		TileEntityRedWire redWire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
		
		if (redWire.activeMasks[side]) {
			return Block.obsidian.getIcon(0, 0);
		} else {
			return redWire.isPowered ? Block.blockRedstone.getIcon(0, 0) : Block.stone.getIcon(0, 0);
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		if (!world.isRemote) {
			((TileEntityRedWire)world.getBlockTileEntity(x, y, z)).scanAndUpdate();
		}
		
		world.markBlockForUpdate(x, y, z);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		
		if (!world.isRemote) {
			((TileEntityRedWire)world.getBlockTileEntity(x, y, z)).updateMasks();
		}
		
		world.markBlockForUpdate(x, y, z);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityRedWire();
	}
	
}
