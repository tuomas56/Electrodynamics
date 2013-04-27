package nanocircuit.world.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import nanocircuit.core.core.CreativeTabNCM;
import nanocircuit.world.lib.Ore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

import java.util.List;
import java.util.Random;

public class BlockOre extends Block {

	public BlockOre(int i) {
		super(i, Material.rock);
		setUnlocalizedName("tile.ncmOre");
		setHardness(3F);
		setResistance(5F);
		setCreativeTab(CreativeTabNCM.block);
	}

	@Override
	public int idDropped(int meta, Random random, int j) {
//		if (meta == Reference.ModInfo.MAGNETITE) {
//			return NanoCircuitCore.itemComponent.itemID;
//		} else {
//			return blockID;
//		}
		//TODO Rewrite - dmillerw
		return 0;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
//		if (meta == Reference.ModInfo.MAGNETITE) {
//			if (Config.CoreConfiguration.IC2) {
//				// Drop 1-2 (add fortune-1) chunks
//				return 1 + random.nextInt(2) + random.nextInt(fortune + 1);
//			}
//			// Drop 4-5 (add fortune-1) dusts
//			return 4 + random.nextInt(2) + random.nextInt(fortune + 1);
//		} else {
//			return 1;
//		}
		//TODO Rewrite - dmillerw
		return 0;
	}

	@Override
	public int damageDropped(int meta) {
//		if (meta == Reference.ModInfo.MAGNETITE) {
//			if (Config.CoreConfiguration.IC2) {
//				return Reference.ModInfo.MAGNETITE_CHUNK;
//			}
//			return Reference.ModInfo.MAGNETITE_DUST;
//		} else {
//			return meta;
//		}
		//TODO Rewrite - dmillerw
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (Ore ore : Ore.values()) {
			subItems.add(ore.toItemStack());
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
		int count = Ore.values().length;

		textures = new Icon[count];
		for (int i = 0; i < count; i++) {
			textures[i] = registry.registerIcon(Ore.get(i).getTextureFile());
		}
	}

}
