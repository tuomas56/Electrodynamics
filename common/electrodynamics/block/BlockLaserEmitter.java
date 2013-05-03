package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityLaserEmitter;
import electrodynamics.util.PlayerUtil;

public class BlockLaserEmitter extends BlockContainer {

	protected BlockLaserEmitter(int id) {
		super(id, Material.iron);
		setHardness(2F);
		setResistance(1F);
		setCreativeTab(CreativeTabED.block);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		if (entity.isSneaking()) {
			world.setBlockMetadataWithNotify(x, y, z, PlayerUtil.determine3DOrientation_F(world, x, y, z, entity).getOpposite().ordinal(), 2);
		} else {
			world.setBlockMetadataWithNotify(x, y, z, PlayerUtil.determine3DOrientation_I(world, x, y, z, entity), 2);
		}
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLaserEmitter();
	}
	
}
