package nanocircuit.core.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.Configuration;

public class CoreConfiguration {

	public static Map<String, Integer> blockIDMapping = new HashMap<String, Integer>();
	public static Map<String, Integer> itemIDMapping = new HashMap<String, Integer>();
	
	public static void initializeDefaults() {
		//Defaults go here
	}
	
	public static void handleConfig(Configuration config) {
		try {
			config.load();
			
			//Stuff
		} catch(Exception e) {
			//Logger here + :(
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	
	public static int getBlockID(String id) {
		return blockIDMapping.get(id);
	}
	
	public static int getItemID(String id) {
		return itemIDMapping.get(id);
	}
	
	public static int getShiftedItemID(String id) {
		return getItemID(id) + 256;
	}
	
}