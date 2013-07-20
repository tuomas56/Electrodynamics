package electrodynamics.addons;

import java.util.ArrayList;

import cpw.mods.fml.common.Loader;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import electrodynamics.Electrodynamics;
import electrodynamics.core.EDLogger;
import electrodynamics.util.StringUtil;
import electrodynamics.addons.computercraft.EDAddonComputerCraft;
import electrodynamics.addons.thermalexpansion.EDAddonThermalExpansion;

public class AddonManager {

	private static ArrayList<EDAddon> addons = new ArrayList<EDAddon>();
	
	public static void loadAddons() {
		if (Loader.isModLoaded("ComputerCraft")) {
			addons.add(new EDAddonComputerCraft());
		}
		
		if (Loader.isModLoaded("ThermalExpansion")) {
			addons.add(new EDAddonThermalExpansion());
		}
	}
	
	/** Called during main mod's PostInit state on server*/
	public static void init() {
		for (EDAddon addon : addons) {
			addon.init();
		}
	}
	
	/** Called during main mod's PostInit state on client*/
	public static void initClient() {
		for (EDAddon addon : addons) {
			addon.initClient();
		}
	}
	
}
