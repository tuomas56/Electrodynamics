package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.Machine;

public class BlockMachine extends BlockContainer {

	public BlockMachine(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public TileEntity createTileEntity(World world, int meta) {
		try {
			return Machine.values()[meta].tileEntity.newInstance();
		} catch (Exception ex) {
			EDLogger.warn("Failed to create TileEntity for machine " + Machine.values()[meta].unlocalizedName);
			return null;
		}
	}
	
	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
