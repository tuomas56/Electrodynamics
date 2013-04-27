package nanocircuit.core.item;

import nanocircuit.core.lib.Component;
import nanocircuit.core.lib.ItemIDs;
import nanocircuit.core.lib.Strings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemComponent;
	public static Item itemPCB;
	
	public static void initializeItems() {
		itemComponent = new ItemComponent(ItemIDs.ITEM_COMPONENT_ID).setUnlocalizedName(Strings.ITEM_COMPONENT_NAME);
		GameRegistry.registerItem(itemComponent, Strings.ITEM_COMPONENT_NAME);
		for (int i=0; i<Component.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(itemComponent, 1, i), Component.values()[i].getLocalizedName("en_US"));
		}
		
		itemPCB = new ItemPCB(ItemIDs.ITEM_PCB_ID).setUnlocalizedName(Strings.ITEM_PCB_NAME);
		GameRegistry.registerItem(itemPCB, Strings.ITEM_PCB_NAME);
		LanguageRegistry.addName(itemPCB, "PCB");
	}
	
}
