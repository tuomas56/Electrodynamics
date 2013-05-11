package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
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
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTreetap();
	}
	
}
