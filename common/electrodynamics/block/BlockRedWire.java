package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import electrodynamics.client.render.block.RenderBlockRedWire;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityRedWire;

public class BlockRedWire extends BlockContainer {

	/** Do wire blocks provide power. Set to false during updates to avoid looping */
	public boolean wiresProvidePower = true;
	
	public static final float wireMinSize = 0.40F;
	public static final float wireMaxSize = 0.6F;
	
	public BlockRedWire(int id) {
		super(id, Material.circuits);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		TileEntityRedWire redWire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
		return redWire.isPowered ? Block.blockRedstone.getIcon(0, 0) : Block.stone.getIcon(0, 0);
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		TileEntityRedWire redWire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
		
		if (!world.isRemote) {
			redWire.scanAndUpdate();
		}
		
		world.markBlockForUpdate(x, y, z);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		
		TileEntityRedWire redWire = (TileEntityRedWire) world.getBlockTileEntity(x, y, z);
		
		if (!world.isRemote) {
			redWire.updateMasks();
			redWire.scanAndUpdate();
		}
		
		world.markBlockForUpdate(x, y, z);
	}
	
	@Override
	public int getRenderType() {
		return RenderBlockRedWire.renderID;
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
