package nanocircuit.core.configuration;

import java.io.File;

import nanocircuit.core.core.NCMLogger;
import nanocircuit.core.lib.BlockIDs;
import nanocircuit.core.lib.ItemIDs;
import nanocircuit.core.lib.Strings;
import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {

	public static final String CATEGORY_SETTINGS = "user_settings";
	
	public static void handleConfig(File file) {
		Configuration config = new Configuration(file);
		
		try {
			/* Settings */
			ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_CONFIGNAME, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_DEFAULT).getInt(ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_DEFAULT);
			ConfigurationSettings.NICKLE_MAX_Y_LEVEL = config.get(CATEGORY_SETTINGS, ConfigurationSettings.NICKLE_MAX_Y_LEVEL_CONFIGNAME, ConfigurationSettings.NICKLE_MAX_Y_LEVEL_DEFAULT).getInt(ConfigurationSettings.NICKLE_MAX_Y_LEVEL_DEFAULT);
			
			/* Block IDs */
			BlockIDs.BLOCK_ORE_ID = config.getBlock(Strings.BLOCK_ORE_NAME, BlockIDs.BLOCK_ORE_DEFAULT_ID).getInt(BlockIDs.BLOCK_ORE_DEFAULT_ID);
			
			/* Item IDs */
			ItemIDs.ITEM_COMPONENT_ID = config.getItem(Strings.ITEM_COMPONENT_NAME, ItemIDs.ITEM_COMPONENT_DEFAULT_ID).getInt(ItemIDs.ITEM_COMPONENT_DEFAULT_ID);
			ItemIDs.ITEM_PCB_ID = config.getItem(Strings.ITEM_PCB_NAME, ItemIDs.ITEM_PCB_DEFAULT_ID).getInt(ItemIDs.ITEM_PCB_DEFAULT_ID);
		} catch (Exception e) {
			NCMLogger.warn("Had trouble reading/writing to the configuration file.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	
}
