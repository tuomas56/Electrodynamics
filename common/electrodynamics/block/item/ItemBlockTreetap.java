package electrodynamics.block.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.tileentity.TileEntityTreetap;

public class ItemBlockTreetap extends ItemBlock {

	public ItemBlockTreetap(int id) {
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float i, float d, float k) {
		super.onItemUse(stack, player, world, x, y, z, side, i, d, k);
		
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		x += sideForge.offsetX;
		y += sideForge.offsetY;
		z += sideForge.offsetZ;
		
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if (tile != null && tile instanceof TileEntityTreetap) {
			((TileEntityTreetap)tile).rotation = ForgeDirection.getOrientation(side);
		}
		
		return true;
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		int xOrig = x - sideForge.offsetX;
		int yOrig = y - sideForge.offsetY;
		int zOrig = z - sideForge.offsetZ;
		
		if (world.getBlockId(xOrig, yOrig, zOrig) != BlockIDs.BLOCK_RUBBER_WOOD_ID) {
			return false;
		}
		
		if (world.getBlockMetadata(xOrig, yOrig, zOrig) != side) {
			return false;
		}
		
		return super.placeBlockAt(stack, player, world, xOrig, yOrig, zOrig, side, hitX, hitY, hitZ, metadata);
	}
	
}
