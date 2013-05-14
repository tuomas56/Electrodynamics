package electrodynamics.module;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.Electrodynamics;
import electrodynamics.configuration.ConfigurationHandler;
import electrodynamics.core.handler.GuiHandler;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.item.EDItems;
import electrodynamics.item.ItemComponent;
import electrodynamics.item.hammer.ItemSledgeHammer;
import electrodynamics.item.hammer.ItemSteelHammer;
import electrodynamics.item.hammer.ItemStoneHammer;
import electrodynamics.item.tesla.ItemArmorTeslaBoots;
import electrodynamics.item.tesla.ItemArmorTeslaChest;
import electrodynamics.item.tesla.ItemArmorTeslaHelm;
import electrodynamics.item.tesla.ItemArmorTeslaLegs;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.Component;
import electrodynamics.lib.item.ItemIDs;

public class EDModuleCore extends EDModule {

	@Override
	public void preInit() {
		ConfigurationHandler.handleConfig(new File(Electrodynamics.instance.configFolder, ModInfo.GENERIC_MOD_ID + ".cfg"));
		
		/* ITEM */
		EDItems.itemComponent = new ItemComponent(ItemIDs.ITEM_COMPONENT_ID).setUnlocalizedName(Strings.ITEM_COMPONENT_NAME);
		GameRegistry.registerItem(EDItems.itemComponent, Strings.ITEM_COMPONENT_NAME);
		for (Component component : Component.values()) {
			EDLanguage.getInstance().registerItemStack(component.toItemStack(), component.unlocalizedName);
		}
		
		EDItems.itemStoneHammer = new ItemStoneHammer(ItemIDs.ITEM_STONE_HAMMER_ID).setUnlocalizedName(Strings.ITEM_STONE_HAMMER_NAME);
		GameRegistry.registerItem(EDItems.itemStoneHammer, Strings.ITEM_STONE_HAMMER_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemStoneHammer);
		
		EDItems.itemSteelHammer = new ItemSteelHammer(ItemIDs.ITEM_STEEL_HAMMER_ID).setUnlocalizedName(Strings.ITEM_STEEL_HAMMER_NAME);
		GameRegistry.registerItem(EDItems.itemSteelHammer, Strings.ITEM_STEEL_HAMMER_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemSteelHammer);
		
		EDItems.itemSledgeHammer = new ItemSledgeHammer(ItemIDs.ITEM_SLEDGE_HAMMER_ID).setUnlocalizedName(Strings.ITEM_SLEDGE_HAMMER_NAME);
		GameRegistry.registerItem(EDItems.itemSledgeHammer, Strings.ITEM_SLEDGE_HAMMER_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemSledgeHammer);
		
		EDItems.itemTeslaHelm = new ItemArmorTeslaHelm(ItemIDs.ITEM_TESLA_HELM_ID).setUnlocalizedName(Strings.ITEM_TESLA_HAT_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaHelm, Strings.ITEM_TESLA_HAT_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaHelm);
		
		EDItems.itemTeslaChest = new ItemArmorTeslaChest(ItemIDs.ITEM_TESLA_CHEST_ID).setUnlocalizedName(Strings.ITEM_TESLA_CHEST_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaChest, Strings.ITEM_TESLA_CHEST_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaChest);
		
		EDItems.itemTeslaLegs = new ItemArmorTeslaLegs(ItemIDs.ITEM_TESLA_LEGS_ID).setUnlocalizedName(Strings.ITEM_TESLA_LEGS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaLegs, Strings.ITEM_TESLA_LEGS_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaLegs);
		
		EDItems.itemTeslaBoots = new ItemArmorTeslaBoots(ItemIDs.ITEM_TESLA_BOOTS_ID).setUnlocalizedName(Strings.ITEM_TESLA_BOOTS_NAME);
		GameRegistry.registerItem(EDItems.itemTeslaBoots, Strings.ITEM_TESLA_BOOTS_NAME);
		EDLanguage.getInstance().registerItem(EDItems.itemTeslaBoots);
	}

	@Override
	public void init() {
		NetworkRegistry.instance().registerGuiHandler(Electrodynamics.instance, new GuiHandler());
		
		// 3 twine to one string
		GameRegistry.addRecipe(new ItemStack(Item.silk), "TTT", 'T', Component.TWINE.toItemStack());
	}

	@Override
	public void postInit() {
		
	}

}
