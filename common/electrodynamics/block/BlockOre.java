package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.BlockIDs;
import electrodynamics.lib.Ore;

public class BlockOre extends Block {

	private Icon[] textures;
	
	public BlockOre(int i) {
		super(i, Material.rock);
		setHardness(3F);
		setResistance(5F);
		setCreativeTab(CreativeTabED.block);
	}

	@Override
	public int idDropped(int meta, Random random, int j) {
		return BlockIDs.BLOCK_ORE_ID;
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (Ore ore : Ore.values()) {
			subItems.add(ore.toItemStack());
		}
	}

	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}

	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Ore.values().length];
		
		for (int i = 0; i < Ore.values().length; i++) {
			textures[i] = registry.registerIcon(Ore.get(i).getTextureFile());
		}
	}

}
