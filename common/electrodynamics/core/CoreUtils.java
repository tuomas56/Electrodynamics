package electrodynamics.core;

import net.minecraft.world.World;

public class CoreUtils {

	public static boolean isClient(World world) {
		return world.isRemote;
	}
	
	public static boolean isServer(World world) {
		return !world.isRemote;
	}
	
}
