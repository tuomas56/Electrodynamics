package electrodynamics.world.gen.feature;

import java.util.HashSet;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class FeatureBase {

	public final String name;
	public final byte type;
	public final boolean regenerate;
	public final HashSet<String> validBiomes = new HashSet<String>();
	
	public FeatureBase(String name, boolean regenerate) {
		this.name = name;
		this.type = 0;
		this.regenerate = regenerate;
	}
	
	public FeatureBase(String name, WorldGenerator worldGenerator, byte type, boolean regenerate) {
		this.name = name;
		this.type = type;
		this.regenerate = regenerate;
	}
	
	public final String getFeatureName() {
		return this.name;
	}
	
	public abstract boolean generateFeature(Random random, int chunkX, int chunkZ, World world, boolean newGeneration);
	
}
