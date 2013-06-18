package electrodynamics.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.Electrodynamics;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class BlockGas extends Block {

	private Icon texture;
	
	public BlockGas(int id) {
		super(id, Electrodynamics.gas);
		setCreativeTab(CreativeTabED.block);
		setTickRandomly(true);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderBlockPass() {
		return 1;
	}
	
	@Override
	public int tickRate(World world) {
		return 5;
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		//TODO
	}
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister registry) {
		this.texture = registry.registerIcon(ModInfo.ICON_PREFIX + "gas/gasNaturalUnrefined");
	}
	
}
