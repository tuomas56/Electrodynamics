package electrodynamics.world.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import electrodynamics.lib.block.BlockIDs;
import electrodynamics.util.BiomeHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class WorldGenPlant {

	public int flowerID;
	public int flowerMeta;

	public List<BiomeGenBase> validBiomes;

	public WorldGenPlant(int id, int meta, BiomeGenBase... validBiomes) {
		this.flowerID = id;
		this.flowerMeta = meta;
		this.validBiomes = Arrays.asList(validBiomes);
	}

	public WorldGenPlant(int id, int meta, ArrayList<BiomeGenBase> validBiomes) {
		this.flowerID = id;
		this.flowerMeta = meta;
		this.validBiomes = validBiomes;
	}

	private static List<WorldGenPlant> generators;

	static {
		generators = new ArrayList<WorldGenPlant>();

		register(new WorldGenPlant(BlockIDs.BLOCK_WORMWOOD_ID, 0, BiomeHelper.getBiomesForTypes(Type.PLAINS, Type.SWAMP, Type.HILLS, Type.FOREST, Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN)));
		register(new WorldGenPlant(BlockIDs.BLOCK_WORMWOOD_ID, 1, BiomeHelper.getBiomesForTypes(Type.DESERT, Type.WASTELAND)));
	}

	public static void register(WorldGenPlant gen) {
		generators.add(gen);
	}

	public static void generate(World world, int chunkX, int chunkZ, Random random) {
		if (world.getWorldInfo().getTerrainType() == WorldType.FLAT) {
			return;
		}

		for (WorldGenPlant gen : generators) {
			int x = (chunkX * 16) + random.nextInt(16);
			int z = (chunkZ * 16) + random.nextInt(16);
			int y = random.nextInt(256);

			gen.generateFlowers(world, x, y, z, gen.flowerID, random);
		}
	}

	private void generateFlowers(World world, int x, int y, int z, int blockID, Random random) {
		for (int l = 0; l < 64; ++l) {
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(4) - random.nextInt(4);
			int k1 = z + random.nextInt(8) - random.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && (!world.provider.hasNoSky || j1 < 127) && Block.blocksList[blockID].canBlockStay(world, i1, j1, k1)) {
				if (validBiomes.contains(world.getBiomeGenForCoords(i1, k1))) {
					world.setBlock(i1, j1, k1, blockID, flowerMeta, 2);
				}
			}
		}
	}

}
