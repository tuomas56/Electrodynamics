package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityRedstoneWire;

public class BlockRedstoneWire extends BlockContainer {

	/** Do wire blocks provide power. Set to false during updates to avoid looping */
	public static boolean wiresProvidePower = true;
	
	public BlockRedstoneWire(int id) {
		super(id, Material.circuits);
		setCreativeTab(CreativeTabED.block);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		
		if (!world.isRemote) {
			//TODO communicate with TE
		}
	}
	
	public void breakBlock(World world, int x, int y, int z, int blockID, int blockMeta) {
		super.breakBlock(world, x, y, z, blockID, blockMeta);
		
		if (!world.isRemote) {
			//TODO communicate with TE
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		if (!world.isRemote) {
			//TODO communicate with TE
			//TODO break if not supported (handle by te?)
		}
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
		return new TileEntityRedstoneWire();
	}
	
}
