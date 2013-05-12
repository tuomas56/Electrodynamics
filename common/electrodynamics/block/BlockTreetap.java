package electrodynamics.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int id) {
		TileEntityTreetap tile = (TileEntityTreetap) world.getBlockTileEntity(x, y, z);
		
		if (tile != null) {
			if (!tile.isTapSupported()) {
				this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this));
				world.setBlockToAir(x, y, z);
			}
		} else {
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this));
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		if (world.isRemote) return false;
		if (player.isSneaking()) return false;
		
		TileEntityTreetap tile = (TileEntityTreetap) world.getBlockTileEntity(x, y, z);
		
		if (tile != null && tile instanceof TileEntityTreetap) {
			if (!tile.hasBucket) {
				if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Item.bucketEmpty && !tile.hasBucket) {
					tile.hasBucket = true;
					tile.dirty = true;
					--player.getCurrentEquippedItem().stackSize;
					
					return true;
				}
			} else {
				if (tile.liquidAmount == 0 || tile.liquidAmount == 1000) {
					tile.dropBucket();
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityTreetap();
	}
	
}
