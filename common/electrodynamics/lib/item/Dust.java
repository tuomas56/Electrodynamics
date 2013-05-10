package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Dust {

	CLAY(Strings.DUST_CLAY, "Clay Dust"),
	COBALT(Strings.DUST_COBALT, "Cobalt Dust"),
	COBALT_HEXAHYDRATE(Strings.DUST_COB_HEX, "Cobalt Hexahydrate Dust"),
	COPPER(Strings.DUST_COPPER, "Copper Dust"),
	LEAD(Strings.DUST_LEAD, "Lead Dust"),
	LIME_PURE(Strings.DUST_LIME_PURE, "Pure Limestone Dust"),
	LIMESTONE(Strings.DUST_LIMESTONE, "Limestone Dust"),
	LITHIUM(Strings.DUST_LITHIUM, "Lithium Dust"),
	MAGNETITE(Strings.DUST_MAGNETITE, "Magnetite Dust"),
	NICKEL(Strings.DUST_NICKEL, "Nickel Dust"),
	SILICON(Strings.DUST_SILICON, "Silicon Dust"), 
	SILICON_SMALL(Strings.DUST_SILICON_SMALL, "Small Pile of Silicon Dust"),
	SULFUR(Strings.DUST_SULFUR, "Sulfur Dust"),
	TELLURIUM(Strings.DUST_TELLURIUM, "Tellurium Dust"),
	TUNGSTEN(Strings.DUST_TUNGSTEN, "Tungsten Dust"),
	LODESTONE(Strings.DUST_LODESTONE, "Lodestone Dust"),
	VOIDSTONE(Strings.DUST_VOIDSTONE, "Voidstone Dust"),
	IRON(Strings.DUST_IRON, "Iron Dust"),
	GOLD(Strings.DUST_GOLD, "Gold Dust"),
	BLUESTONE(Strings.DUST_BLUESTONE, "Bluestone Dust"),
	GREENSTONE(Strings.DUST_GREENSTONE, "Greenstone Dust");
	
	public String unlocalizedName;
	public String localizedName;
	
	private Dust(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "dust/" + unlocalizedName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_DUST_ID + 256, 1, this.ordinal());
	}
	
	public void registerWithOreDictionary() {
		OreDictionary.registerOre(unlocalizedName, this.toItemStack());
	}
	
	public static Dust get(int ordinal) {
		return Dust.values()[ordinal];
	}
	
}
