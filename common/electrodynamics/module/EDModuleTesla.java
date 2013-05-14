package electrodynamics.module;

import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemTeslaModule;
import electrodynamics.item.tesla.ItemTeslaArmor;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.ItemIDs;
import electrodynamics.lib.item.TeslaModule;

public class EDModuleTesla extends EDModule {

	public void preInit() {
		EDItems.itemTeslaHelm = new ItemTeslaArmor(ItemIDs.ITEM_TESLA_HELM_ID, 0).setUnlocalizedName(Strings.ITEM_TESLA_HAT_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaHelm, Strings.ITEM_TESLA_HAT_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaHelm);
		
		EDItems.itemTeslaChest = new ItemTeslaArmor(ItemIDs.ITEM_TESLA_CHEST_ID, 1).setUnlocalizedName(Strings.ITEM_TESLA_CHEST_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaChest, Strings.ITEM_TESLA_CHEST_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaChest);
		
		EDItems.itemTeslaLegs = new ItemTeslaArmor(ItemIDs.ITEM_TESLA_LEGS_ID, 2).setUnlocalizedName(Strings.ITEM_TESLA_LEGS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaLegs, Strings.ITEM_TESLA_LEGS_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaLegs);
		
		EDItems.itemTeslaBoots = new ItemTeslaArmor(ItemIDs.ITEM_TESLA_BOOTS_ID, 3).setUnlocalizedName(Strings.ITEM_TESLA_BOOTS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaBoots, Strings.ITEM_TESLA_BOOTS_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaBoots);
	
		EDItems.itemTeslaModule = new ItemTeslaModule(ItemIDs.ITEM_TESLA_MODULE_ID).setUnlocalizedName(Strings.ITEM_TESLA_MODULE);
		GameRegistry.registerItem(EDItems.itemTeslaModule, Strings.ITEM_TESLA_MODULE);
		for (TeslaModule module : TeslaModule.values()) {
			EDLanguage.getInstance().registerItemStack(module.toItemStack(), module.unlocalizedName);
		}
	}
	
	public void init() {
		
	}
	
	public void postInit() {
		
	}
	
}
