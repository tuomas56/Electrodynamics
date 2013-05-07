package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Grinding {

	CHARCOPYRITE(Strings.GROUND_CHARCOPYRITE, "Ground Charcopyrite"),
	COLBALTITE(Strings.GROUND_COBALTITE, "Ground Cobaltite"),
	GALENA(Strings.GROUND_GALENA, "Ground Galena"),
	LITHIUM(Strings.GROUND_LITHIUM, "Ground Lithium"),
	MAGNETITE(Strings.GROUND_MAGNETITE, "Ground Magnetite"),
	NICKEL(Strings.GROUND_NICKEL, "Ground Nickel"),
	TUNGSTEN(Strings.GROUND_TUNGSTEN, "Ground Tungsten"),
	DIAMOND(Strings.GROUND_DIAMOND, "Ground Diamond"),
	EMERALD(Strings.GROUND_EMERALD, "Ground Emerald"), 
	GOLD(Strings.GROUND_GOLD, "Ground Gold"), 
	IRON(Strings.GROUND_IRON, "Ground Iron"), 
	LAPIS(Strings.GROUND_LAPIS, "Ground Lapis"),
	REDSTONE(Strings.GROUND_REDSTONE, "Ground Redstone");
	
	public String unlocalizedName;
	public String localizedName;
	
	private Grinding(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "dust/ground/" + unlocalizedName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_DUST_ID + 256, 1, this.ordinal() + Dust.values().length);
	}
	
	public static Grinding get(int ordinal) {
		return Grinding.values()[ordinal];
	}
	
}
