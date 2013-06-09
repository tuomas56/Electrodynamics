package electrodynamics.world.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.common.IWorldGenerator;
import electrodynamics.block.EDBlocks;
import electrodynamics.util.BlockUtil;

public class WorldGenWormwood implements IWorldGenerator {

	public static final Type[] WORMWOOD_VALID_BIOMES = new Type[] {Type.PLAINS, Type.SWAMP, Type.HILLS, Type.FOREST, Type.JUNGLE, Type.MOUNTAIN};
	public static final Type[] DRY_WORMWOOD_VALID_BIOMES = new Type[] {Type.DESERT, Type.WASTELAND};
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		handleWormwood(world, chunkX, chunkZ);
	}

	public void handleWormwood(World world, int chunkX, int chunkZ) {
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
		List<Type> biomeTypes = Arrays.asList(BiomeDictionary.getTypesForBiome(biome));
		
		for (Type type : WORMWOOD_VALID_BIOMES) {
			if (biomeTypes.contains(type)) {
				generateWormwood(world, chunkX, chunkZ, 0);
			}
		}
		
		for (Type type : DRY_WORMWOOD_VALID_BIOMES) {
			if (biomeTypes.contains(type)) {
				generateWormwood(world, chunkX, chunkZ, 1);
			}
		}
	}
	
	public void generateWormwood(World world, int chunkX, int chunkZ, int meta) {
		final int COUNT = 16;
		
		Random random = new Random();
		
		//Generation starts in middle of selected chunk
		int x = (chunkX * 16) + 8;
		int z = (chunkZ * 16) + 8;
		int y = BlockUtil.getFirstUncoveredYPos(world, chunkX, chunkZ);
		
		for (int i=0; i<COUNT; i++) {
			//Generation spans from slightly off the middle to same point across
			x = x + random.nextInt(8) - random.nextInt(4);
			z = z + random.nextInt(8) - random.nextInt(4);

			if (world.isAirBlock(x, y, z) && (!world.provider.hasNoSky || y < 127) && Block.blocksList[EDBlocks.blockWormwood.blockID].canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, EDBlocks.blockWormwood.blockID, meta, 2);
			}
		}
	}
	
}
