package electrodynamics.world.gen.feature;

import java.util.Random;

import electrodynamics.lib.block.Decorative;
import electrodynamics.world.gen.feature.FeatureHandler.FeatureType;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.Configuration;

public class FeatureLimestone extends FeatureBase {

	public int radius;
	
	public FeatureLimestone(String name) {
		super(name);
	}

	@Override
	public void generateFeature(Random random, int chunkX, int chunkZ, World world, boolean newGeneration) {
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
								if ((i1 >= chunkX * 16 && i1 < (chunkX * 16) + 16) && (j1 >= chunkZ * 16 && j1 < (chunkZ * 16) + 16)) {
									int j2 = world.getBlockId(i1, i2, j1);

									if (j2 == Block.dirt.blockID || j2 == Block.stone.blockID) {
										world.setBlock(i1, i2, j1, Decorative.LIMESTONE.ordinal(), 0, 2);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void handleConfig(Configuration config) {
		this.radius = config.get(FeatureHandler.getFeatureCategory(FeatureType.LIMESTONE), "radius", 25).getInt(25);
	}
	
}
