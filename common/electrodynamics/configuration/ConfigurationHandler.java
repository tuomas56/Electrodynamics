package electrodynamics.configuration;

import java.io.File;

import cpw.mods.fml.client.registry.RenderingRegistry;

import electrodynamics.Electrodynamics;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.ItemIDs;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler {

	public static final String CATEGORY_SETTINGS = "user.settings";
	public static final String CATEGORY_TESLA = "tesla_armor.settings";
	public static final String CATEGORY_KEYS = "user.keybindings";
	public static final String CATEGORY_GRAPHICS = "user.graphics";
	public static final String CATEGORY_SOUND = "user.sound";
	
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
			
			ConfigurationSettings.MAGNETIC_RANGE = config.get(CATEGORY_TESLA, ConfigurationSettings.MAGNETIC_RANGE_CONFIGNAME, ConfigurationSettings.MAGNETIC_RANGE_DEFAULT).getDouble(ConfigurationSettings.MAGNETIC_RANGE_DEFAULT);
			ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED = config.get(CATEGORY_TESLA, ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_CONFIGNAME, ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_DEFAULT).getDouble(ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED_DEFAULT);
			ConfigurationSettings.THERMAL_VIEW_RANGE = config.get(CATEGORY_TESLA, ConfigurationSettings.THERMAL_VIEW_RANGE_CONFIGNAME, ConfigurationSettings.THERMAL_VIEW_RANGE_DEFAULT).getDouble(ConfigurationSettings.THERMAL_VIEW_RANGE_DEFAULT);
			
			/* Graphical Settings */
			ConfigurationSettings.VOIDSTONE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
			ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS = config.get(CATEGORY_GRAPHICS, ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS_NAME, ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS_DEFAULT).getBoolean(ConfigurationSettings.VOIDSTONE_FANCY_GRAPHICS_DEFAULT);
			
			/* Audio settings */
			ConfigurationSettings.VOIDSTONE_AMBIENT_SOUND = config.get(CATEGORY_SOUND, ConfigurationSettings.VOIDSTONE_AMBIENT_SOUND_NAME, ConfigurationSettings.VOIDSTONE_AMBIENT_SOUND_DEFAULT).getBoolean(ConfigurationSettings.VOIDSTONE_AMBIENT_SOUND_DEFAULT);
			
			/* Key Bindings */
			Electrodynamics.proxy.setKeyBinding(ConfigurationSettings.MAGNET_TOGGLE_NAME, config.get(CATEGORY_KEYS, ConfigurationSettings.MAGNET_TOGGLE_CONFIGNAME, ConfigurationSettings.MAGNET_TOGGLE_DEFAULT).getInt(ConfigurationSettings.MAGNET_TOGGLE_DEFAULT), false);
			Electrodynamics.proxy.setKeyBinding(ConfigurationSettings.THERMAL_VIEW_TOGGLE_NAME, config.get(CATEGORY_KEYS, ConfigurationSettings.THERMAL_VIEW_TOGGLE_CONFIGNAME, ConfigurationSettings.THERMAL_VIEW_TOGGLE_DEFAULT).getInt(ConfigurationSettings.THERMAL_VIEW_TOGGLE_DEFAULT), false);
			Electrodynamics.proxy.setKeyBinding(ConfigurationSettings.HOVER_MODE_TOGGLE_NAME, config.get(CATEGORY_KEYS, ConfigurationSettings.HOVER_MODE_TOGGLE_CONFIGNAME, ConfigurationSettings.HOVER_MODE_TOGGLE_DEFAULT).getInt(ConfigurationSettings.HOVER_MODE_TOGGLE_DEFAULT), false);
			
			/* Block IDs */
			BlockIDs.BLOCK_ORE_ID = config.getBlock(Strings.BLOCK_ORE_NAME, BlockIDs.BLOCK_ORE_DEFAULT_ID).getInt(BlockIDs.BLOCK_ORE_DEFAULT_ID);
			BlockIDs.BLOCK_RED_WIRE_ID = config.getBlock(Strings.BLOCK_RED_WIRE_NAME, BlockIDs.BLOCK_RED_WIRE_DEFAULT_ID).getInt(BlockIDs.BLOCK_RED_WIRE_DEFAULT_ID);
			BlockIDs.BLOCK_TABLE_ID = config.getBlock(Strings.BLOCK_TABLE_NAME, BlockIDs.BLOCK_TABLE_DEFAULT_ID).getInt(BlockIDs.BLOCK_TABLE_DEFAULT_ID);
			BlockIDs.BLOCK_LASER_EMITTER_ID = config.getBlock(Strings.BLOCK_LASER_EMITTER, BlockIDs.BLOCK_LASER_EMITTER_DEFAULT_ID).getInt(BlockIDs.BLOCK_LASER_EMITTER_DEFAULT_ID);
			BlockIDs.BLOCK_MACHINE_ID = config.getBlock(Strings.BLOCK_MACHINE, BlockIDs.BLOCK_MACHINE_DEFAULT_ID).getInt(BlockIDs.BLOCK_MACHINE_DEFAULT_ID);
			BlockIDs.BLOCK_DECORATIVE_ID = config.getBlock(Strings.BLOCK_DECORATIVE, BlockIDs.BLOCK_DECORATIVE_DEFAULT_ID).getInt(BlockIDs.BLOCK_DECORATIVE_DEFAULT_ID);
			BlockIDs.BLOCK_WORMSEED_ID = config.getBlock(Strings.BLOCK_WORMSEED, BlockIDs.BLOCK_WORMSEED_DEFAULT_ID).getInt(BlockIDs.BLOCK_WORMSEED_DEFAULT_ID);
			BlockIDs.BLOCK_LITHIUM_CLAY_ID = config.getBlock(Strings.BLOCK_LITHIUM_CLAY, BlockIDs.BLOCK_LITHIUM_CLAY_DEFAULT_ID).getInt(BlockIDs.BLOCK_LITHIUM_CLAY_DEFAULT_ID);
			BlockIDs.BLOCK_GAS_ID = config.getBlock(Strings.BLOCK_GAS, BlockIDs.BLOCK_GAS_DEFAULT_ID).getInt(BlockIDs.BLOCK_GAS_DEFAULT_ID);
			BlockIDs.BLOCK_STORAGE_ID = config.getBlock(Strings.BLOCK_STORAGE, BlockIDs.BLOCK_STORAGE_DEFAULT_ID).getInt(BlockIDs.BLOCK_STORAGE_DEFAULT_ID);
			
			/* Item IDs */
			ItemIDs.ITEM_COMPONENT_ID = config.getItem(Strings.ITEM_COMPONENT_NAME, ItemIDs.ITEM_COMPONENT_DEFAULT_ID).getInt(ItemIDs.ITEM_COMPONENT_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_HELM_ID = config.getItem(Strings.ITEM_TESLA_HAT_NAME, ItemIDs.ITEM_TESLA_HELM_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_HELM_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_CHEST_ID = config.getItem(Strings.ITEM_TESLA_CHEST_NAME, ItemIDs.ITEM_TESLA_CHEST_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_CHEST_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_LEGS_ID = config.getItem(Strings.ITEM_TESLA_LEGS_NAME, ItemIDs.ITEM_TESLA_LEGS_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_LEGS_DEFAULT_ID);
			ItemIDs.ITEM_TESLA_BOOTS_ID = config.getItem(Strings.ITEM_TESLA_BOOTS_NAME, ItemIDs.ITEM_TESLA_BOOTS_DEFAULT_ID).getInt(ItemIDs.ITEM_TESLA_BOOTS_DEFAULT_ID);
			ItemIDs.ITEM_STONE_HAMMER_ID = config.getItem(Strings.ITEM_STONE_HAMMER_NAME, ItemIDs.ITEM_STONE_HAMMER_DEFAULT_ID).getInt(ItemIDs.ITEM_STONE_HAMMER_DEFAULT_ID);
			ItemIDs.ITEM_STEEL_HAMMER_ID = config.getItem(Strings.ITEM_STEEL_HAMMER_NAME, ItemIDs.ITEM_STEEL_HAMMER_DEFAULT_ID).getInt(ItemIDs.ITEM_STEEL_HAMMER_DEFAULT_ID);
			ItemIDs.ITEM_SLEDGE_HAMMER_ID = config.getItem(Strings.ITEM_SLEDGE_HAMMER_NAME, ItemIDs.ITEM_SLEDGE_HAMMER_DEFAULT_ID).getInt(ItemIDs.ITEM_SLEDGE_HAMMER_DEFAULT_ID);
			ItemIDs.ITEM_DUST_ID = config.getItem(Strings.ITEM_DUST_NAME, ItemIDs.ITEM_DUST_DEFAULT_ID).getInt(ItemIDs.ITEM_DUST_DEFAULT_ID);
			ItemIDs.ITEM_INGOT_ID = config.getItem(Strings.ITEM_INGOT_NAME, ItemIDs.ITEM_INGOT_DEFAULT_ID).getInt(ItemIDs.ITEM_INGOT_DEFAULT_ID);
			ItemIDs.ITEM_HANDHELD_SIEVE_ID = config.getItem(Strings.ITEM_HANDHELD_SIEVE_NAME, ItemIDs.ITEM_HANDHELD_SIEVE_DEFAULT_ID).getInt(ItemIDs.ITEM_HANDHELD_SIEVE_DEFAULT_ID);
			ItemIDs.ITEM_TRAY_ID = config.getItem(Strings.ITEM_TRAY_NAME, ItemIDs.ITEM_TRAY_DEFAULT_ID).getInt(ItemIDs.ITEM_TRAY_DEFAULT_ID);
			ItemIDs.ITEM_PLASMA_RIFLE = config.getItem(Strings.ITEM_PLASMA_RIFLE, ItemIDs.ITEM_PLASMA_RIFLE_ID).getInt(ItemIDs.ITEM_PLASMA_RIFLE_ID);
			
		} catch (Exception e) {
			EDLogger.warn("Had trouble reading/writing to the configuration file.");
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	
}
