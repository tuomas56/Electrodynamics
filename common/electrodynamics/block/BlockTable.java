package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketTableUpdate;
import electrodynamics.tileentity.TileEntityTable;
import electrodynamics.util.BlockUtil;

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

//	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
//		if (world.isRemote) return;
//		
//		if (entity instanceof EntityItem) {
//			TileEntityTable table = (TileEntityTable) world.getBlockTileEntity(x, y, z);
//
//			if (table.displayedItem == null) {
//				EntityItem item = (EntityItem) entity;
//				
//				if (item.delayBeforeCanPickup == 0) {
//					ItemStack toAdd = null;
//					
//					if (item.getEntityItem().stackSize > 1) {
//						toAdd = item.getEntityItem().copy();
//						toAdd.stackSize = 1;
//						item.getEntityItem().stackSize--;
//					} else {
//						toAdd = item.getEntityItem();
//						item.setDead();
//					}
//					
//					table.displayedItem = toAdd;
//					PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.fillPacket(new PacketTableUpdate(x, y, z, toAdd)), world.provider.dimensionId);
//					world.markBlockForUpdate(x, y, z);
//				}
//			}
//		}
//	}

	public void breakBlock(World world, int x, int y, int z, int i1, int i2) {
		TileEntityTable table = (TileEntityTable) world.getBlockTileEntity(x, y, z);

		if (table != null) {
			ItemStack itemstack = table.displayedItem;

			if (itemstack != null) {
				BlockUtil.dropItemFromBlock(world, x, y, z, itemstack, this.random);
			}
		}

		super.breakBlock(world, x, y, z, i1, i2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		if (world.isRemote) return true;
		if (player.isSneaking()) return false;
		
		TileEntityTable table = (TileEntityTable) world.getBlockTileEntity(x, y, z);
		
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == EDItems.itemSteelHammer) {
			table.handleSmash(player, player.getCurrentEquippedItem());
		} else {
			if (table.displayedItem != null) {
				BlockUtil.dropItemFromBlock(world, x, y, z, table.displayedItem, this.random);
				table.displayedItem = null;
			} else {
				if (player.getCurrentEquippedItem() != null) {
					table.displayedItem = player.getCurrentEquippedItem().copy();
					table.displayedItem.stackSize = 1;
					
					if (player.getCurrentEquippedItem().stackSize > 1) {
						player.getCurrentEquippedItem().stackSize--;
					} else {
						player.inventory.mainInventory[player.inventory.currentItem] = null;
					}
				}
			}
			
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.fillPacket(new PacketTableUpdate(x, y, z, table.displayedItem)), world.provider.dimensionId);
			world.markBlockForUpdate(x, y, z);
		}
		
		return true;
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
