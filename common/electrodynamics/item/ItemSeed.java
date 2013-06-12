package electrodynamics.item;

import electrodynamics.block.EDBlocks;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ItemSeed extends Item  {

	public ItemSeed(int itemID) {
		super( itemID );
		setCreativeTab( CreativeTabED.resource );
	}

	@Override
	public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if( side != 1 ) { // must be planted on top of a block.
			return false;
		}
		if( player.canPlayerEdit(x, y, z, side, item) && player.canPlayerEdit(x, y+ 1, z, side, item) ) {
			int i1 = world.getBlockId(x, y, z);
			Block soil = Block.blocksList[i1];

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, (IPlantable) EDBlocks.blockWormwood) && world.isAirBlock(x, y+ 1, z)) {
				world.setBlock( x, y + 1, z, EDBlocks.blockWormwood.blockID, 0, 3 );
				--item.stackSize;
				return true;
			}
		}
		return false;
	}

	@Override
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon( ModInfo.ICON_PREFIX + "plant/wormwoodSeeds" );
    }

}
