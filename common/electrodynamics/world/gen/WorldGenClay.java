package electrodynamics.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenClay implements IWorldGenerator {

	public int blockID;

	public int numPerSpawn;

	public WorldGenClay(int id, int num) {
		this.blockID = id;
		this.numPerSpawn = num;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int y = random.nextInt(128);
		int z = chunkZ * 16;

		if (world.getBlockMaterial(x, y, z) != Material.water) {
			return;
		} else {
			int l = random.nextInt(this.numPerSpawn + 1);
			byte b0 = 1;

			for (int i1 = x - l; i1 <= x + l; ++i1) {
				for (int j1 = z - l; j1 <= z + l; ++j1) {
					int k1 = i1 - x;
					int l1 = j1 - z;

					if (k1 * k1 + l1 * l1 <= l * l) {
						for (int i2 = y - b0; i2 <= y + b0; ++i2) {
							int j2 = world.getBlockId(i1, i2, j1);

							if (j2 == Block.dirt.blockID || j2 == Block.blockClay.blockID) {
								world.setBlock(i1, i2, j1, this.blockID, 0, 2);
							}
						}
					}
				}
			}
		}
	}

}
