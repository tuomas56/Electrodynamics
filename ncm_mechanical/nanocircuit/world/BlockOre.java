package nanocircuit.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nanocircuit.NanoCircuitCore;
import nanocircuit.core.Config;
import nanocircuit.core.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

import java.util.List;
import java.util.Random;

public class BlockOre extends Block {

	public BlockOre(int i) {
		super( i, Material.rock );
		setUnlocalizedName( "tile.ncmOre" );
		setHardness( 3F );
		setResistance( 5F );
		setCreativeTab( NanoCircuitCore.tabsNCM );
	}

	@Override
	public int idDropped(int meta, Random random, int j) {
		if( meta == Reference.ORE_META.MAGNETITE ) {
			return NanoCircuitCore.itemComponent.itemID;
		} else {
			return blockID;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		if( meta == Reference.ORE_META.MAGNETITE ) {
			if( Config.MODS_INSTALLED.IC2 ) {
				//Drop 1-2 (add fortune-1) chunks
				return 1 + random.nextInt( 2 ) + random.nextInt( fortune + 1 );
			}
			//Drop 4-5 (add fortune-1) dusts
			return 4 + random.nextInt( 2 ) + random.nextInt( fortune + 1 );
		} else {
			return 1;
		}
	}

	@Override
	public int damageDropped(int meta) {
		if( meta == Reference.ORE_META.MAGNETITE ) {
			if( Config.MODS_INSTALLED.IC2 ) {
				return Reference.COMPONENT_META.MAGNETITE_CHUNK;
			}
			return Reference.COMPONENT_META.MAGNETITE_DUST;
		} else {
			return meta;
		}
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for( Ores ore : Ores.values() ) {
			subItems.add( ore.toItemStack() );
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
		int count = Ores.values().length;

		textures = new Icon[count];
		for( int i = 0; i < count; i++ ) {
			textures[i] = registry.registerIcon( Ores.get( i ).getTextureFile() );
		}
	}

}
