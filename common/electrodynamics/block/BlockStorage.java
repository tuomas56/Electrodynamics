package electrodynamics.block;

import java.util.List;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.Storage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockStorage extends Block {

	private Icon[] textures;
	
	public BlockStorage(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setCreativeTab(CreativeTabED.block);
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (Storage storage : Storage.values()) {
			list.add(storage.toItemStack());
		}
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Storage.values().length];
		
		for (Storage storage : Storage.values()) {
			textures[storage.ordinal()] = registry.registerIcon(storage.getTextureFile());
		}
	}
	
}
