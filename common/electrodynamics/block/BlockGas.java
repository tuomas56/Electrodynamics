package electrodynamics.block;

import java.util.List;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.Gas;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockGas extends Block {

	private Icon[] textures;
	
	public BlockGas(int id) {
		super(id, Material.air);
		setCreativeTab(CreativeTabED.block);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (Gas gas : Gas.values()) {
			list.add(gas.toItemStack());
		}
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Gas.values().length];
		
		for (Gas gas : Gas.values()) {
			textures[gas.ordinal()] = registry.registerIcon(gas.getTextureFile());
		}
	}
	
}
