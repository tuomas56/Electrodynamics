package nanocircuit.world.core;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {
	
	private int blockID;
	private int blockMeta;
	private int blockAmount;
	private int yMax;

	private int dimension;
	
	public WorldGenOre(int blockID, int blockMeta, int blockAmount, int yMax) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
		this.blockAmount = blockAmount;
		this.yMax = yMax;
		this.dimension = 0;
	}

	public WorldGenOre(int blockID, int blockMeta, int blockAmount, int yMax, int dimension) {
		this(blockID, blockMeta, blockAmount, yMax);
		this.dimension = dimension;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.dimensionId == dimension) generate(world, random, chunkX, chunkZ);
	}

	private void generate(World world, Random rand, int chunkX, int chunkZ) {
        for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(yMax);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(blockID, blockMeta, blockAmount, 1)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}

}
