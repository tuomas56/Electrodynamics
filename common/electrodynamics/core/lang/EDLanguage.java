package electrodynamics.core.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import electrodynamics.Electrodynamics;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.core.ModInfo;

public class EDLanguage {

	public static final String LANG_DIR = "/mods/" + ModInfo.GENERIC_MOD_ID.toLowerCase() + "/lang/";
	
	public boolean errorShown = false;
	
	/** Key is language name, value is properties file */
	public HashMap<String, Properties> languageMapping = new HashMap<String, Properties>();
	
	private static EDLanguage instance;
	
	public static EDLanguage getInstance() {
		if (instance == null) instance = new EDLanguage();
		return instance;
	}
	
	public EDLanguage() {
		loadDefaultLanguage("en_US");
		loadDefaultLanguage("es_ES");
		
		File langDir = new File(Electrodynamics.instance.configFolder, "lang");
		if (!langDir.exists()) {
			langDir.mkdirs();
		}
	}
	
	private void loadDefaultLanguage(String lang) {
		Properties langFile = new Properties((Properties) this.languageMapping.get(lang));
		InputStreamReader reader = null;
		
		try {
			InputStream inStream = getClass().getResourceAsStream(LANG_DIR + lang + ".properties");
			if (inStream == null) {
				throw new IOException();
			}
			reader = new InputStreamReader(inStream, "UTF-8");
			langFile.load(reader);
		} catch(Exception ex) {
			EDLogger.warn("Failed to load language: " + lang + " Reason: " + ex.getLocalizedMessage());
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
				}
		}

		this.languageMapping.put(lang, langFile);
		EDLogger.info("Loaded language file: " + lang);
	}
	
	public String translate(String tag, String lang) {
		if (languageMapping.containsKey(lang)) {
			return ((Properties)languageMapping.get(lang)).getProperty(tag);
		}
		
		EDLogger.warn(tag + " was not found for language: " + lang);
		return "";
	}
	
	public void registerItem(Item item) {
		registerItemStack(new ItemStack(item), item.getUnlocalizedName());
	}
	
	public void registerBlock(Block block) {
		registerItemStack(new ItemStack(block), block.getUnlocalizedName());
	}
	
	public void registerItemStack(ItemStack stack, String unlocalized) {
		String currLang = "";
		
		try {
			for (String lang : languageMapping.keySet()) {
				currLang = lang;
				LanguageRegistry.instance().addNameForObject(stack, lang, translate(unlocalized, lang));
			}
		} catch(Exception ex) {
			if (ConfigurationSettings.SHOW_LOCALIZATION_ERRORS) {
				EDLogger.warn("Failed to register " + currLang + " localization for " + unlocalized);
			} else {
				if (!errorShown) {
					EDLogger.warn("Ran into an issue with localization. This message will only show once, but you can enable localization errors in the config to see more info.");
					errorShown = true;
				}
			}
		}
	}
	
}
