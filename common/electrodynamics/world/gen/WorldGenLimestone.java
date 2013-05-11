package electrodynamics.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenLimestone implements IWorldGenerator {

	public int blockID;

	public int radius;

	public WorldGenLimestone(int id, int num) {
		this.blockID = id;
		this.radius = num;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.getWorldInfo().getTerrainType() == WorldType.FLAT) {
			return;
		}
		
		int x = chunkX * 16;
		int y = random.nextInt(64);
		int z = chunkZ * 16;

		if (world.getBlockId(x, y, z) != 0) {
			return;
		} else {
			int l = random.nextInt(this.radius - 2) + 2;
			byte b0 = 2;

			for (int i1 = x - l; i1 <= x + l; ++i1) {
				for (int j1 = z - l; j1 <= z + l; ++j1) {
					int k1 = i1 - x;
					int l1 = j1 - z;

					if (k1 * k1 + l1 * l1 <= l * l) {
						for (int i2 = y - b0; i2 <= y + b0; ++i2) {
							if (i2 > 0) {
								if (world.getChunkFromBlockCoords(i1, j1).isTerrainPopulated && world.getChunkFromBlockCoords(i1, j1).isChunkLoaded) {
									int j2 = world.getBlockId(i1, i2, j1);

									if (j2 == Block.dirt.blockID || j2 == Block.stone.blockID) {
										world.setBlock(i1, i2, j1, this.blockID, 0, 2);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
