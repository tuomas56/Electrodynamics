package electrodynamics.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import electrodynamics.lib.ItemIDs;
import electrodynamics.lib.Strings;

public class ItemHandler {

	public static Item itemSteelHammer;
	
	public static void createNewStoneHammer() {
		itemSteelHammer = new ItemSteelHammer(ItemIDs.ITEM_STEEL_HAMMER_ID).setUnlocalizedName(Strings.ITEM_STEEL_HAMMER_NAME);
		GameRegistry.registerItem(itemSteelHammer, Strings.ITEM_STEEL_HAMMER_NAME);
		LanguageRegistry.addName(itemSteelHammer, "Steel Hammer");
	}
	
}
