package electrodynamics.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class BlockDecorative extends Block {

	private Icon[] textures;
	
	public static String[] blockNames = new String[] {"Limestone Bricks"};
	public static String[] blockSubNames = new String[] {"brickLimestone"};
	
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
	public void registerIcons(IconRegister registry) {
		textures = new Icon[blockSubNames.length];
		
		for (int i = 0; i < textures.length; i++) {
			textures[i] = registry.registerIcon(ModInfo.ICON_PREFIX + "decorative/" + blockSubNames[i]);
		}
	}
	
}
