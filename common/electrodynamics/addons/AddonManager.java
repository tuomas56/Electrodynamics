package electrodynamics.addons;

import java.io.File;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

import cpw.mods.fml.common.Loader;

import net.minecraft.crash.CallableMinecraftVersion;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import electrodynamics.Electrodynamics;
import electrodynamics.addons.computercraft.EDAddonComputerCraft;
import electrodynamics.core.EDLogger;
import electrodynamics.util.StringUtil;

public class AddonManager {

	public static final String ADDON_CONFIG_FILE = "EDAddons.cfg";
	public static final String CATEGORY_ADDONS = "addons";
	
	private static Map<Addon, EDAddon> addons = new EnumMap<Addon, EDAddon>(Addon.class);
	
	private static EnumSet<Addon> loadedAddons = EnumSet.noneOf(Addon.class);
	
	public static EDAddon forceGetAddon(Addon a) {
		return addons.get(a);
	}
	
	public static EDAddon getAddon(Addon a) {
		if (loadedAddons.contains(a)) {
			return addons.get(a);
		}
		
		EDLogger.warn("Something tried to call for an addon that's disabled.");
		return null;
	}
	
	public static Configuration getConfig() {
		Configuration config = new Configuration(new File(Electrodynamics.instance.configFolder, ADDON_CONFIG_FILE));
		config.load();
		
		config.addCustomCategoryComment(CATEGORY_ADDONS, "Enable/Disable addons as you see fit.\nBe warned however that some addons depend on certain mods, and will disable themselves if said dependency isn't found.");
	
		//Removes default categories, as I have no use for them here
		config.removeCategory(config.getCategory(Configuration.CATEGORY_BLOCK));
		config.removeCategory(config.getCategory(Configuration.CATEGORY_ITEM));
		config.removeCategory(config.getCategory(Configuration.CATEGORY_GENERAL));
		
		return config;
	}
	
	public static void loadAddons() {
		registerAddon(Addon.COMPUTERCRAFT, new EDAddonComputerCraft());
		
		Configuration config = getConfig();
		
		for (Addon addon : Addon.values()) {
			if (isEnabled(config, addon)) {
				EDAddon instance = addons.get(addon);
				
				if (instance != null) {
					if (instance.init()) {
						loadedAddons.add(addon);
						EDLogger.info("Loading addon " + addon.toString());
					} else {
						EDLogger.warn("Failed to load addon " + addon.toString());
					}
				} else {
					EDLogger.warn("Addon " + addon.toString() + " is missing a mapping!");
				}
			}
		}
		
		config.save();
	}
	
	/** Called during main mod's PostInit state on server*/
	public static void init() {
		for (Addon addon : loadedAddons) {
			init(addon);
		}
	}
	
	private static void init(Addon a) {
		addons.get(a).init();
	}
	
	/** Called during main mod's PostInit state on client*/
	public static void initClient() {
		for (Addon addon : loadedAddons) {
			initClient(addon);
		}
	}
	
	private static void initClient(Addon a) {
		addons.get(a).initClient();
	}
	
	private static boolean isEnabled(Configuration config, Addon addon) {
		StringBuilder sb = new StringBuilder();
		EDAddon instance = forceGetAddon(addon);
		Property moduleEnabled = config.get(CATEGORY_ADDONS, addon.toString(), true);
		return moduleEnabled.getBoolean(true);
	}
	
	private static void registerAddon(Addon addon, EDAddon addonInstance) {
		addons.put(addon, addonInstance);
	}
	
	public static enum Addon {
		COMPUTERCRAFT;
	}
	
}
