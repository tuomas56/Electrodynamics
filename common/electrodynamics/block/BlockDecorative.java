package electrodynamics.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class BlockDecorative extends Block {

	private Icon[] textures;
	
	public static String[] blockNames = new String[] {"Limestone", "Limestone Brick", "Brittle Limestone", "Smooth Limestone", "Limestone Bricks"};
	public static String[] blockSubNames = new String[] {"blockLimestone", "brickLimestone", "brittleLimestone", "smoothLimestone", "bricksLimestone"};
	
	public BlockDecorative(int id) {
		super(id, Material.rock);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}

	@Override
	public int damageDropped(int meta) {
		if (meta == 0) {
			return 2;
		}
		
		return meta;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[blockSubNames.length];
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = registry.registerIcon(ModInfo.ICON_PREFIX + "decorative/" + blockSubNames[i]);
		}
	}
	
}
