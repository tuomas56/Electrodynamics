package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityTreetap;

public class BlockTreetap extends BlockContainer {

	public BlockTreetap(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (tile != null && tile instanceof TileEntityTreetap) {
			if (!((TileEntityTreetap)tile).isSupported()) {
				this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this));
				world.setBlockToAir(x, y, z);
			}
		} else {
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this));
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTreetap();
	}
	
}
