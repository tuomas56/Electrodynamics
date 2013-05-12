package electrodynamics.world.gen;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.common.IWorldGenerator;
import electrodynamics.block.EDBlocks;

public class WorldGenRubberTree implements IWorldGenerator {

	public int maxTreeHeight;

	public ArrayList<BiomeGenBase> validBiomes;

	public WorldGenRubberTree(int height, ArrayList<BiomeGenBase> biomes) {
		this.maxTreeHeight = height;
		this.validBiomes = biomes;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		generate(world, random, chunkX, 10, chunkZ);
	}

	public boolean generate(World world, Random random, int x, int count, int z) {
		for (; count > 0; count--) {
			x += random.nextInt(15) - 7;
			z += random.nextInt(15) - 7;
			
			int y = 256 - 1;
			while ((world.getBlockId(x, y - 1, z) == 0) && (y > 0))
				y--;
			
			if (validBiomes.contains(world.getBiomeGenForCoords(x, z))) {
				if (!grow(world, x, y, z, random)) {
					count -= 3;
				}
			}
		}
		return true;
	}

	public boolean grow(World world, int x, int y, int z, Random random) {
		if ((world == null) || (EDBlocks.blockRubberWood == null)) {
			return false;
		}
		
		int h = getGrowHeight(world, x, y, z);
		if (h < 2) {
			return false;
		}

		int height = h / 2;
		h -= h / 2;
		height += random.nextInt(h + 1);
		for (int i = 0; i < height; i++) {
			world.setBlock(x, y + i, z, EDBlocks.blockRubberWood.blockID, 1, 7);

			if ((height < 4) || ((height < 7) && (i > 1)) || (i > 2)) {
				for (int a = x - 2; a <= x + 2; a++) {
					for (int b = z - 2; b <= z + 2; b++) {
						int c = i + 4 - height;
						if (c < 1) {
							c = 1;
						}
						boolean gen = ((a > x - 2) && (a < x + 2) && (b > z - 2) && (b < z + 2)) || ((a > x - 2) && (a < x + 2) && (random.nextInt(c) == 0)) || ((b > z - 2) && (b < z + 2) && (random.nextInt(c) == 0));

						if ((gen) && (world.getBlockId(a, y + i, b) == 0)) {
							world.setBlock(a, y + i, b, EDBlocks.blockRubberLeaves.blockID, 0, 7);
						}
					}
				}
			}
		}
		
		for (int a = x - 1; a <= x + 1; a++) {
			for (int b = z - 1; b <= z + 1; b++) {
				world.setBlock(a, y + height, b, EDBlocks.blockRubberLeaves.blockID, 0, 7);
			}
		}
		
		return true;
	}

	public int getGrowHeight(World world, int x, int y, int z) {
		int id = world.getBlockId(x, y - 1, z);
		if ((Block.blocksList[id] == null) || (!Block.blocksList[id].canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (IPlantable) Block.blocksList[EDBlocks.blockRubberSapling.blockID])) || ((world.getBlockId(x, y, z) != 0) && (world.getBlockId(x, y, z) != EDBlocks.blockRubberSapling.blockID))) {
			return 0;
		}
		int height = 1;
		for (; (world.getBlockId(x, y + 1, z) == 0) && (height < 8); y++) {
			height++;
		}
		return height;
	}

}
