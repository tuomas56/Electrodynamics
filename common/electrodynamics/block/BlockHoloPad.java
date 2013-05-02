package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityHoloPad;

public class BlockHoloPad extends BlockContainer {

	public BlockHoloPad(int id) {
		super(id, Material.iron);
		setHardness(2F);
		setCreativeTab(CreativeTabED.block);
		setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
		setLightOpacity(1);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return Block.blockIron.getIcon(0, 0);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityHoloPad();
	}
	
}
