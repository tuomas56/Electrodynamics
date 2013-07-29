package electrodynamics.world.gen.feature;

import java.util.Random;

import electrodynamics.lib.block.Decorative;
import electrodynamics.lib.block.BlockIDs;
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

      int x = chunkX * 16 + 8;
      int z = chunkZ * 16 + 8;
      for(int y=5; y <= 64; y++){

        if (world.getBlockId(x, y, z) != 0) {
          continue;
        } else {
          int l = random.nextInt(this.radius - 2) + 2;
          byte b0 = 2;

          for (int i1 = x - l; i1 <= x + l; ++i1) {
            for (int j1 = z - l; j1 <= z + l; ++j1) {
              int k1 = i1 - x;
              int l1 = j1 - z;

              if (k1 * k1 + l1 * l1 <= l * l) {
                if ((i1 >= chunkX * 16 && i1 < (chunkX * 16) + 16) && (j1 >= chunkZ * 16 && j1 < (chunkZ * 16) + 16)) {
                  int j2 = world.getBlockId(i1, y, j1);

                  if (j2 == Block.dirt.blockID || j2 == Block.stone.blockID) {
                    world.setBlock(i1, y, j1, BlockIDs.BLOCK_DECORATIVE_ID, 0, Decorative.LIMESTONE.ordinal());
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
