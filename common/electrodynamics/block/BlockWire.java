package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityWire;

public class BlockWire extends BlockContainer {
	
	public BlockWire(int id) {
		super(id, Material.circuits);
		this.setUnlocalizedName("edBlockWire");
		this.setCreativeTab(CreativeTabED.block);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWire();
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		if (tile instanceof TileEntityWire) {
			((TileEntityWire) tile).updateEntity();
		}
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int par5) {
		TileEntityWire wire = (TileEntityWire) blockAccess.getBlockTileEntity(x, y, z);

		if (wire.isPowered()) {
			return 15;
		} else {
			return 0;
		}
	}

}
