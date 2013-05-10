package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Ingot {

	LODESTONE(Strings.INGOT_LODESTONE, "Lodestone Ingot"),
	
	COBALT(Strings.INGOT_COBALT, "Cobolt Ingot"),
	COPPER(Strings.INGOT_COPPER, "Copper Ingot"),
	LEAD(Strings.INGOT_LEAD, "Lead Ingot"), 
	NICKEL(Strings.INGOT_NICKEL, "Nickel Ingot"),
	TELLURIUM(Strings.INGOT_TELLURIUM, "Tellurium Ingot"),
	TUNGSTEN(Strings.INGOT_TUNGSTEN, "Tungsten Ingot"),
	STEEL(Strings.INGOT_STEEL, "Steel Ingot");
	
	public String unlocalizedName;
	public String localizedName;
	
	private Ingot(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "ingot/" + unlocalizedName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_INGOT_ID + 256, 1, this.ordinal());
	}
	
	public void registerWithOreDictionary() {
		OreDictionary.registerOre(unlocalizedName, this.toItemStack());
	}
	
	public static Ingot get(int ordinal) {
		return Ingot.values()[ordinal];
	}
	
}
