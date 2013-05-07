package electrodynamics.module;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.Electrodynamics;
import electrodynamics.configuration.ConfigurationHandler;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemComponent;
import electrodynamics.item.tesla.ItemArmorTeslaBoots;
import electrodynamics.item.tesla.ItemArmorTeslaChest;
import electrodynamics.item.tesla.ItemArmorTeslaHelm;
import electrodynamics.item.tesla.ItemArmorTeslaLegs;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.ItemIDs;

public class EDModuleCore extends EDModule {

	@Override
	public void preInit() {
		ConfigurationHandler.handleConfig(Electrodynamics.instance.configFolder);
		
		/* ITEM */
		EDItems.itemComponent = new ItemComponent(ItemIDs.ITEM_COMPONENT_ID).setUnlocalizedName(Strings.ITEM_COMPONENT_NAME);
		GameRegistry.registerItem(EDItems.itemComponent, Strings.ITEM_COMPONENT_NAME);
		for (int i=0; i<Component.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(EDItems.itemComponent, 1, i), Component.values()[i].getLocalizedName("en_US"));
		}
		
		EDItems.itemTeslaHelm = new ItemArmorTeslaHelm(ItemIDs.ITEM_TESLA_HELM_ID).setUnlocalizedName(Strings.ITEM_TESLA_HAT_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaHelm, Strings.ITEM_TESLA_HAT_NAME);
		LanguageRegistry.addName(EDItems.itemTeslaHelm, "Tesla Helmet");
		
		EDItems.itemTeslaChest = new ItemArmorTeslaChest(ItemIDs.ITEM_TESLA_CHEST_ID).setUnlocalizedName(Strings.ITEM_TESLA_CHEST_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaChest, Strings.ITEM_TESLA_CHEST_NAME);
		LanguageRegistry.addName(EDItems.itemTeslaChest, "Tesla Chestplate");
		
		EDItems.itemTeslaLegs = new ItemArmorTeslaLegs(ItemIDs.ITEM_TESLA_LEGS_ID).setUnlocalizedName(Strings.ITEM_TESLA_LEGS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaLegs, Strings.ITEM_TESLA_LEGS_NAME);
		LanguageRegistry.addName(EDItems.itemTeslaLegs, "Tesla Leggings");
		
		EDItems.itemTeslaBoots = new ItemArmorTeslaBoots(ItemIDs.ITEM_TESLA_BOOTS_ID).setUnlocalizedName(Strings.ITEM_TESLA_BOOTS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaBoots, Strings.ITEM_TESLA_BOOTS_NAME);
		LanguageRegistry.addName(EDItems.itemTeslaBoots, "Tesla Boots");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
