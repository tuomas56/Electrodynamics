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
		setCreativeTab(CreativeTabED.block);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		if (!world.isRemote) {
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Item.dyePowder && player.getCurrentEquippedItem().getItemDamage() == 15) {
				(new WorldGenRubberTree(10, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.JUNGLE))).grow(world, x, y, z, new Random());
			}
			return true; 
		}
		
		return false;
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
