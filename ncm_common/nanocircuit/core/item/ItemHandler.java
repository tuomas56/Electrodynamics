package nanocircuit.core.item;

import cpw.mods.fml.common.registry.GameRegistry;
import nanocircuit.core.core.CoreConfiguration;
import net.minecraft.item.Item;

public class ItemHandler {

	public static Item itemComponent;
	public static Item itemPCB;
	
	public static void initializeItems() {
		itemComponent = new ItemComponent(CoreConfiguration.getItemID("itemComponent")).setUnlocalizedName("itemComponent");
		GameRegistry.registerItem(itemComponent, "itemComponent");
		//TODO Localization
		
		itemPCB = new ItemPCB(CoreConfiguration.getItemID("itemPCB")).setUnlocalizedName("itemPCB");
		GameRegistry.registerItem(itemPCB, "itemPCB");
		//TODO Localization
	}
	
}
