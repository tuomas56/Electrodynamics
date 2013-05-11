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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.api.tool.ITool;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.TileEntityTable;
import electrodynamics.util.BlockUtil;

public class BlockTable extends BlockContainer {

	public static String[] subNames = new String[] {Strings.BASIC_TABLE, Strings.SMASH_TABLE};
	
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
		
		if (player.getCurrentEquippedItem() != null && (player.getCurrentEquippedItem().getItem() instanceof ITool) && table.hasRecipe(player.getCurrentEquippedItem())) {
			table.handleSmash(player, player.getCurrentEquippedItem());
			((ITool)player.getCurrentEquippedItem().getItem()).onToolUsed(player.getCurrentEquippedItem(), world, x, y, z, player);
		} else {
			if (table.getItem() != null) {
				BlockUtil.dropItemFromBlock(world, x, y, z, table.displayedItem.copy(), this.random);
				table.setItem(null);
			} else {
				if (player.getCurrentEquippedItem() != null) {
					ItemStack toDisplay = player.getCurrentEquippedItem().copy();
					toDisplay.stackSize = 1;
					table.setItem(toDisplay);
					
					if (player.getCurrentEquippedItem().stackSize > 1) {
						player.getCurrentEquippedItem().stackSize--;
					} else {
						player.inventory.mainInventory[player.inventory.currentItem] = null;
					}
				}
			}
			
			table.update();
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
		for (int i = 0; i < subNames.length; i++) {
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
