package electrodynamics.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.item.tesla.ItemArmorTeslaBoots;
import electrodynamics.item.tesla.ItemArmorTeslaChest;
import electrodynamics.item.tesla.ItemArmorTeslaHelm;
import electrodynamics.item.tesla.ItemArmorTeslaLegs;
import electrodynamics.lib.Component;
import electrodynamics.lib.ItemIDs;
import electrodynamics.lib.Strings;

public class ItemHandler {

	public static Item itemComponent;
	public static Item itemPCB;
	public static Item itemTeslaHelm;
	public static Item itemTeslaChest;
	public static Item itemTeslaLegs;
	public static Item itemTeslaBoots;
	
	public static void initializeItems() {
		itemComponent = new ItemComponent(ItemIDs.ITEM_COMPONENT_ID).setUnlocalizedName(Strings.ITEM_COMPONENT_NAME);
		GameRegistry.registerItem(itemComponent, Strings.ITEM_COMPONENT_NAME);
		for (int i=0; i<Component.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(itemComponent, 1, i), Component.values()[i].getLocalizedName("en_US"));
		}
		
		itemPCB = new ItemPCB(ItemIDs.ITEM_PCB_ID).setUnlocalizedName(Strings.ITEM_PCB_NAME);
		GameRegistry.registerItem(itemPCB, Strings.ITEM_PCB_NAME);
		LanguageRegistry.addName(itemPCB, "PCB");
		
		itemTeslaHelm = new ItemArmorTeslaHelm(ItemIDs.ITEM_TESLA_HELM_ID).setUnlocalizedName(Strings.ITEM_TESLA_HAT_NAME);
		GameRegistry.registerItem(itemTeslaHelm, Strings.ITEM_TESLA_HAT_NAME);
		LanguageRegistry.addName(itemTeslaHelm, "Tesla Helmet");
		
		itemTeslaChest = new ItemArmorTeslaChest(ItemIDs.ITEM_TESLA_CHEST_ID).setUnlocalizedName(Strings.ITEM_TESLA_CHEST_NAME);
		GameRegistry.registerItem(itemTeslaChest, Strings.ITEM_TESLA_CHEST_NAME);
		LanguageRegistry.addName(itemTeslaChest, "Tesla Chestplate");
		
		itemTeslaLegs = new ItemArmorTeslaLegs(ItemIDs.ITEM_TESLA_LEGS_ID).setUnlocalizedName(Strings.ITEM_TESLA_LEGS_NAME);
		GameRegistry.registerItem(itemTeslaLegs, Strings.ITEM_TESLA_LEGS_NAME);
		LanguageRegistry.addName(itemTeslaLegs, "Tesla Leggings");
		
		itemTeslaBoots = new ItemArmorTeslaBoots(ItemIDs.ITEM_TESLA_BOOTS_ID).setUnlocalizedName(Strings.ITEM_TESLA_BOOTS_NAME);
		GameRegistry.registerItem(itemTeslaBoots, Strings.ITEM_TESLA_BOOTS_NAME);
		LanguageRegistry.addName(itemTeslaBoots, "Tesla Boots");
	}
	
}
