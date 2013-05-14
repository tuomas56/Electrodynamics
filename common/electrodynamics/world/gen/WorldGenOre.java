package electrodynamics.world.gen;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {
	
	private int blockID;
	private int blockMeta;
	private int blockAmount;
	private int yMax;
	private int yMin = 0;
	private int rarity;
	private int dimension;
	
	public WorldGenOre(int blockID, int blockMeta, int blockAmount, int yMax, int rarity) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
		this.blockAmount = blockAmount;
		this.yMax = yMax;
		this.rarity = rarity;
		this.dimension = 0;
	}

	public WorldGenOre(int blockID, int blockMeta, int blockAmount, int yMin, int yMax, int rarity, int dimension) {
		this(blockID, blockMeta, blockAmount, yMax, rarity);
		this.dimension = dimension;
		this.yMin = yMin;
	}
	
	public WorldGenOre(int blockID, int blockMeta, int blockAmount, int yMin, int yMax, int rarity) {
		this(blockID, blockMeta, blockAmount, yMax, rarity);
		this.yMin = yMin;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.getWorldInfo().getTerrainType() == WorldType.FLAT) {
			return;
		}
		
		if (world.provider.dimensionId == dimension) generate(world, random, chunkX, chunkZ);
	}

	private void generate(World world, Random rand, int chunkX, int chunkZ) {
        for(int i=0; i<rarity; i++){
        	int firstBlockXCoord = chunkX * rand.nextInt(16);
        	int firstBlockYCoord = MathHelper.getRandomIntegerInRange(rand, yMin, yMax);
        	int firstBlockZCoord = chunkZ * rand.nextInt(16);

        	(new WorldGenMinable(blockID, blockMeta, blockAmount, 1)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}

}
