package nanocircuit.world;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import nanocircuit.blocks.BlockManager;
import nanocircuit.core.Reference;

public class WorldGenNCOre implements IWorldGenerator
{
	WorldGenMinable nickelGen;
	WorldGenMinable magnetiteGen;
	
	public WorldGenNCOre()
	{
		magnetiteGen = new WorldGenMinable(BlockManager.blockOre.blockID, Reference.BLOCK_MAGNETITE_META, 8);
		nickelGen = new WorldGenMinable(BlockManager.blockOre.blockID, Reference.BLOCK_NICKEL_META, 8);
	}
	
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
    {    	
    	chunkX = chunkX << 4;
    	chunkZ = chunkZ << 4;
    	BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(chunkX, chunkZ);
    	
    	//Don't generate ores in Nether and End
    	if (biomegenbase.biomeID != BiomeGenBase.sky.biomeID && biomegenbase.biomeID != BiomeGenBase.hell.biomeID)
    	{
    		this.genStandardOre(chunkX, chunkZ, world, random, 20, this.magnetiteGen, 0, 64);
    		this.genStandardOre(chunkX, chunkZ, world, random, 20, this.nickelGen, 0, 64);
    	}
    }
    
    protected void genStandardOre(int chunkX, int chunkZ, World world, Random random, int rarity, WorldGenerator worldGen, int above_bedrock, int range)
    {
        for (int i = 0; i < rarity; ++i)
        {
            int randx = chunkX + random.nextInt(16);
            int randy = random.nextInt(range - above_bedrock) + above_bedrock;
            int randz = chunkZ + random.nextInt(16);
            worldGen.generate(world, random, randx, randy, randz);
        }
    }

}
