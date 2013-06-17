package electrodynamics.block;

import java.util.Random;

import electrodynamics.core.CreativeTabED;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.block.Gas;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGas extends Block {

	private Icon[] textures;
	
	private Gas gas;
	
	public BlockGas(int id, Gas gas) {
		super(id, Material.air);
		setCreativeTab(CreativeTabED.block);
		setTickRandomly(true);
		setUnlocalizedName(gas.unlocalizedName);
		
		EDLanguage.getInstance().registerBlock(this);
		
		this.gas = gas;
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
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return textures[this.gas.ordinal()];
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		textures = new Icon[Gas.values().length];
		
		for (Gas gas : Gas.values()) {
			textures[gas.ordinal()] = registry.registerIcon(gas.getTextureFile());
		}
	}
	
}
