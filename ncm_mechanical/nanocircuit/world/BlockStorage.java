package nanocircuit.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nanocircuit.NanoCircuitCore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

import java.util.List;
import java.util.Random;

public class BlockStorage extends Block {
	public BlockStorage(int i) {
		super( i, Material.iron );
		setHardness( 3F );
		setResistance( 5F );
		setCreativeTab( NanoCircuitCore.tabsNCM );
	}

	@Override
	public int idDropped(int meta, Random random, int j) {
		return blockID;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		return 1;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for( StorageBlocks sb : StorageBlocks.values() ) {
			subItems.add( sb.toItemStack() );
		}
	}

	// Textures

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}

	@Override
	public void registerIcons(IconRegister registry) {
		int count = StorageBlocks.values().length;

		textures = new Icon[count];
		for( int i = 0; i < count; i++ ) {
			textures[i] = registry.registerIcon( StorageBlocks.get( i ).getTextureFile() );
		}
	}

}
