package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.machine.TileEntityLaserEmitter;
import electrodynamics.util.MathUtil;
import electrodynamics.util.PlayerUtil;

public class BlockLaserEmitter extends BlockContainer {

	public BlockLaserEmitter(int id) {
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
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.getBlockTileEntity(x, y, z) != null && world.getBlockTileEntity(x, y, z) instanceof TileEntityLaserEmitter) {
			TileEntityLaserEmitter laser = (TileEntityLaserEmitter) world.getBlockTileEntity(x, y, z);
			
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Item.dyePowder) {
				ItemStack dye = player.getCurrentEquippedItem();
				
				float r = EntitySheep.fleeceColorTable[MathUtil.reverseNumber(dye.getItemDamage(), 0, 15)][0];
				float g = EntitySheep.fleeceColorTable[MathUtil.reverseNumber(dye.getItemDamage(), 0, 15)][1];
				float b = EntitySheep.fleeceColorTable[MathUtil.reverseNumber(dye.getItemDamage(), 0, 15)][2];
				
				laser.laser.setRGB(r, g, b);
			}
		}
		
		return true;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityLaserEmitter();
	}
	
}
