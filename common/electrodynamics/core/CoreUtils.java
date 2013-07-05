package electrodynamics.core;

import electrodynamics.lib.core.ModInfo;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.world.World;

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
