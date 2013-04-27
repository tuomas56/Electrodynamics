package nanocircuit.core.item;

import nanocircuit.core.core.CoreConfiguration;
import nanocircuit.core.lib.Component;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemComponent;
	public static Item itemPCB;
	
	public static void initializeItems() {
		itemComponent = new ItemComponent(CoreConfiguration.getItemID("itemComponent")).setUnlocalizedName("itemComponent");
		GameRegistry.registerItem(itemComponent, "itemComponent");
		for (int i=0; i<Component.values().length; i++) {
			LanguageRegistry.addName(new ItemStack(itemComponent, 1, i), Component.values()[i].getLocalizedName("en_US"));
		}
		
		itemPCB = new ItemPCB(CoreConfiguration.getItemID("itemPCB")).setUnlocalizedName("itemPCB");
		GameRegistry.registerItem(itemPCB, "itemPCB");
		LanguageRegistry.addName(itemPCB, "PCB");
	}
	
}
