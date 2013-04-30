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
			ConfigurationSettings.MAGNETITE_ENABLED = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETITE_ENABLED_CONFIGNAME, ConfigurationSettings.MAGNETITE_ENABLED_DEFAULT).getBoolean(ConfigurationSettings.MAGNETITE_ENABLED_DEFAULT);
			ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_CONFIGNAME, ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_DEFAULT).getInt(ConfigurationSettings.MAGNETITE_MAX_Y_LEVEL_DEFAULT);
			ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT_CONFIGNAME, ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT_DEFAULT).getInt(ConfigurationSettings.MAGNETITE_SPAWN_AMOUNT_DEFAULT);
			ConfigurationSettings.MAGNETITE_SPAWN_RARITY = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETITE_SPAWN_RARITY_CONFIGNAME, ConfigurationSettings.MAGNETITE_SPAWN_RARITY_DEFAULT).getInt(ConfigurationSettings.MAGNETITE_SPAWN_RARITY_DEFAULT);

			ConfigurationSettings.NICKEL_ENABLED = config.get(CATEGORY_SETTINGS, ConfigurationSettings.NICKEL_ENABLED_CONFIGNAME, ConfigurationSettings.NICKEL_ENABLED_DEFAULT).getBoolean(ConfigurationSettings.NICKEL_ENABLED_DEFAULT);
			ConfigurationSettings.NICKEL_MAX_Y_LEVEL = config.get(CATEGORY_SETTINGS, ConfigurationSettings.NICKEL_MAX_Y_LEVEL_CONFIGNAME, ConfigurationSettings.NICKEL_MAX_Y_LEVEL_DEFAULT).getInt(ConfigurationSettings.NICKEL_MAX_Y_LEVEL_DEFAULT);
			ConfigurationSettings.NICKEL_SPAWN_AMOUNT = config.get(CATEGORY_SETTINGS, ConfigurationSettings.NICKEL_SPAWN_AMOUNT_CONFIGNAME, ConfigurationSettings.NICKEL_SPAWN_AMOUNT_DEFAULT).getInt(ConfigurationSettings.NICKEL_SPAWN_AMOUNT_DEFAULT);
			ConfigurationSettings.NICKEL_SPAWN_RARITY = config.get(CATEGORY_SETTINGS, ConfigurationSettings.NICKEL_SPAWN_RARITY_CONFIGNAME, ConfigurationSettings.NICKEL_SPAWN_RARITY_DEFAULT).getInt(ConfigurationSettings.NICKEL_SPAWN_RARITY_DEFAULT);
			
			ConfigurationSettings.MAGNETIC_RANGE = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETIC_RANGE_CONFIGNAME, ConfigurationSettings.MAGNETIC_RANGE_DEFAULT).getDouble(ConfigurationSettings.MAGNETIC_RANGE_DEFAULT);
			ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED = config.get(CATEGORY_SETTINGS, ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_CONFIGNAME, ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_DEFAULT).getDouble(ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_DEFAULT);
			
			/* Block IDs */
			BlockIDs.BLOCK_ORE_ID = config.getBlock(Strings.BLOCK_ORE_NAME, BlockIDs.BLOCK_ORE_DEFAULT_ID).getInt(BlockIDs.BLOCK_ORE_DEFAULT_ID);
			
			/* Item IDs */
			ItemIDs.ITEM_COMPONENT_ID = config.getItem(Strings.ITEM_COMPONENT_NAME, ItemIDs.ITEM_COMPONENT_DEFAULT_ID).getInt(ItemIDs.ITEM_COMPONENT_DEFAULT_ID);
			ItemIDs.ITEM_PCB_ID = config.getItem(Strings.ITEM_PCB_NAME, ItemIDs.ITEM_PCB_DEFAULT_ID).getInt(ItemIDs.ITEM_PCB_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_HELM_ID = config.getItem(Strings.ITEM_TESLA_HAT_NAME, ItemIDs.ITEM_TESLA_HELM_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_HELM_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_CHEST_ID = config.getItem(Strings.ITEM_TESLA_CHEST_NAME, ItemIDs.ITEM_TESLA_CHEST_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_CHEST_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_LEGS_ID = config.getItem(Strings.ITEM_TESLA_LEGS_NAME, ItemIDs.ITEM_TESLA_LEGS_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_LEGS_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_BOOTS_ID = config.getItem(Strings.ITEM_TESLA_BOOTS_NAME, ItemIDs.ITEM_TESLA_BOOTS_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_BOOTS_DEFAULT_ID);
		} catch (Exception e) {
			NCMLogger.warn("Had trouble reading/writing to the configuration file.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	
}
