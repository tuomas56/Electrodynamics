package electrodynamics.world.gen.feature;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.Configuration;

public abstract class FeatureBase {

	public final String name;
	public final byte type;
	public final HashSet<String> validBiomes = new HashSet<String>();
	
	public FeatureBase(String name) {
		this.name = name;
		this.type = 0;
	}
	
	public FeatureBase(String name, WorldGenerator worldGenerator, byte type) {
		this.name = name;
		this.type = type;
	}
	
	public final String getFeatureName() {
		return this.name;
	}
	
	public abstract void generateFeature(Random random, int chunkX, int chunkZ, World world, boolean newGeneration);
	
	public abstract void handleConfig(Configuration config);
	
}
