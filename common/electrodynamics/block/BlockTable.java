package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CreativeTabED;
import electrodynamics.tileentity.TileEntityTable;

public class BlockTable extends BlockContainer {

	public static String[] blockNames = new String[] { "Display Table", "Smashing Table" };
	public static String[] subNames = new String[] { "blockTableDisplay", "blockTableSmash" };

	public Random random = new Random();
	
	public BlockTable(int id) {
		super(id, Material.wood);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
		setBlockBounds(0, 0, 0, 1, 0.87F, 1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityItem) {
			TileEntityTable table = (TileEntityTable) world.getBlockTileEntity(x, y, z);

			if (table.displayedItem == null) {
				table.displayedItem = ((EntityItem) entity).getEntityItem();
				entity.setDead();
			}
		}
	}

	public void breakBlock(World world, int x, int y, int z, int i1, int i2) {
		TileEntityTable table = (TileEntityTable) world.getBlockTileEntity(x, y, z);

		if (table != null) {
			ItemStack itemstack = table.displayedItem;

			if (itemstack != null) {
				float f = this.random.nextFloat() * 0.8F + 0.1F;
				float f1 = this.random.nextFloat() * 0.8F + 0.1F;
				EntityItem entityitem;

				for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
					int k1 = this.random.nextInt(21) + 10;

					if (k1 > itemstack.stackSize) {
						k1 = itemstack.stackSize;
					}

					itemstack.stackSize -= k1;
					entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
					float f3 = 0.05F;
					entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
					entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
					entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

					if (itemstack.hasTagCompound()) {
						entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
					}
				}
			}

			world.func_96440_m(x, y, z, i1);
		}

		super.breakBlock(world, x, y, z, i1, i2);
	}

	@Override
	public Icon getIcon(int side, int meta) {
		return Block.planks.getIcon(0, 0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i = 0; i < blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}

	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityTable((byte) meta);
	}

	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
