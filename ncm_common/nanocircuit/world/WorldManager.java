package nanocircuit.world;

import cpw.mods.fml.common.registry.GameRegistry;

public class WorldManager 
{
	public static WorldGenNCOre genOre;
	
	public static void initWorld()
	{
		genOre = new WorldGenNCOre();
		GameRegistry.registerWorldGenerator(genOre);
	}

}
