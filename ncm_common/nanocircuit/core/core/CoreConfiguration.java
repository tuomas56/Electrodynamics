package nanocircuit.core.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraftforge.common.Configuration;

public class CoreConfiguration {

	public static Map<String, Integer> blockIDMapping = new HashMap<String, Integer>();
	public static Map<String, Integer> itemIDMapping = new HashMap<String, Integer>();
	
	public static void initializeDefaults() {
		//Defaults go here
		itemIDMapping.put("itemComponent", 1500);
		itemIDMapping.put("itemPCB", 1501);
	}
	
	public static void handleConfig(File file) {
		Configuration config = new Configuration(file);
		
		try {
			config.load();
			
			if (blockIDMapping.size() > 0) {
				for (Entry<String, Integer> blockMapping : blockIDMapping.entrySet()) {
					blockIDMapping.put(blockMapping.getKey(), config.getBlock(blockMapping.getKey(), blockMapping.getValue()).getInt());
				}
			}
			
			if (itemIDMapping.size() > 0) {
				for (Entry<String, Integer> itemMapping : itemIDMapping.entrySet()) {
					itemIDMapping.put(itemMapping.getKey(), config.getBlock(itemMapping.getKey(), itemMapping.getValue()).getInt());
				}
			}
		} catch(Exception e) {
			NCMLogger.warn("Failed to load configuration file! Assuming defaults!");
			initializeDefaults();
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