package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Ingot {

	LODESTONE(Strings.INGOT_LODESTONE, "ingotLodestone"),
	
	COBALT(Strings.INGOT_COBALT, "ingotCobalt"),
	COPPER(Strings.INGOT_COPPER, "ingotCopper"),
	LEAD(Strings.INGOT_LEAD, "ingotLead"), 
	NICKEL(Strings.INGOT_NICKEL, "ingotNickel"),
	TELLURIUM(Strings.INGOT_TELLURIUM, "ingotTellurium"),
	TUNGSTEN(Strings.INGOT_TUNGSTEN, "ingotTungsten"),
	STEEL(Strings.INGOT_STEEL, "ingotSteel"),
	TIN(Strings.INGOT_TIN, "ingotTin"),
	URANIUM(Strings.INGOT_URANIUM, "ingotUranium"),
	SILVER(Strings.INGOT_SILVER, "ingotSilver");
	
	public String unlocalizedName;
	public String textureFile;
	
	private Ingot(String unlocalizedName, String textureFile) {
		this.unlocalizedName = unlocalizedName;
		this.textureFile = textureFile;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "ingot/" + textureFile;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_INGOT_ID + 256, 1, this.ordinal());
	}
	
	public static Ingot get(int ordinal) {
		return Ingot.values()[ordinal];
	}
	
}
