package electrodynamics.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.Decorative;

public class BlockDecorative extends Block {

	private Icon[] textures;
	
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
		for (Decorative dec : Decorative.values()) {
			list.add(dec.toItemStack());
		}
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Decorative.values().length];
		
		for (Decorative dec : Decorative.values()) {
			textures[dec.ordinal()] = registry.registerIcon(dec.getTextureFile());
		}
	}
	
}
