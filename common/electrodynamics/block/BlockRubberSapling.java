package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.util.BiomeHelper;
import electrodynamics.world.gen.WorldGenRubberTree;

public class BlockRubberSapling extends BlockSapling {

	private Icon texture;
	
	public BlockRubberSapling(int id) {
		super(id);
		setCreativeTab(CreativeTabED.resource);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		ItemStack heldItem = player.getCurrentEquippedItem();
		if( heldItem != null && heldItem.getItem() == Item.dyePowder && heldItem.getItemDamage() == 15 ) {
			if( !world.isRemote ) {
				(new WorldGenRubberTree(10, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.JUNGLE))).grow(world, x, y, z, new Random());
				if( !player.capabilities.isCreativeMode )
					heldItem.stackSize--;
			}
			return true;
		}
		return false;
	}
	
	public void markOrGrowMarked(World world, int x, int y, int z, Random random) {
		int l = world.getBlockMetadata(x, y, z);

		if ((l & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, l | 8, 4);
		} else {
			(new WorldGenRubberTree(10, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.JUNGLE))).grow(world, x, y, z, new Random());
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return texture;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "world/plant/rubberSapling");
	}
	
}
