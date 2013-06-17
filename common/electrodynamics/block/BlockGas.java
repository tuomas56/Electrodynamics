package electrodynamics.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.block.Gas;

public class BlockGas extends Block {

	private Icon[] textures;
	
	public BlockGas(int id) {
		super(id, Material.air);
		setCreativeTab(CreativeTabED.block);
		setTickRandomly(true);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int tickRate(World world) {
		return 5;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		world.setBlockToAir(x, y, z);
		
		world.setBlock(x, y + 1, z, id, meta, 3);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (Gas gas : Gas.values()) {
			list.add(gas.toItemStack());
		}
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[metadata];
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Gas.values().length];
		
		for (Gas gas : Gas.values()) {
			textures[gas.ordinal()] = registry.registerIcon(gas.getTextureFile());
		}
	}
	
}
