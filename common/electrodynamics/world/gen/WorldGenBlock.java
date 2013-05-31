package electrodynamics.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenBlock implements IWorldGenerator {

	public int blockID;
	public int blockMeta;
	public int blockAmount;
	
	public int yMax;
	
	public WorldGenBlock(int id, int meta, int count, int yMax) {
		this.blockID = id;
		this.blockMeta = meta;
		this.blockAmount = count;
		this.yMax = yMax;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int i=0; i<blockAmount; i++) {
			int x = (chunkX * 16) + random.nextInt(16);
			int y = random.nextInt(yMax);
			int z = (chunkZ * 16) + random.nextInt(16);
			
			if (world.getBlockId(x, y, z) == Block.stone.blockID) {
				world.setBlock(x, y, z, this.blockID, this.blockMeta, 2);
				
				onGenned(world, x, y, z, random);
			}
		}
	}

	public void onGenned(World world, int x, int y, int z, Random random) {}
	
}
