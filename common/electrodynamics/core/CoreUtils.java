package electrodynamics.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import electrodynamics.lib.core.ModInfo;

public class CoreUtils {

	public static boolean isClient(World world) {
		return world.isRemote;
	}
	
	public static boolean isServer(World world) {
		return !world.isRemote;
	}
	
	public static ResourceLocation getResource(String path) {
		return new ResourceLocation(ModInfo.GENERIC_MOD_ID.toLowerCase(), path);
	}
	
}
