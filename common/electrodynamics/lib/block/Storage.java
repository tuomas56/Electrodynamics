package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.lib.item.Ingot;

public enum Storage {

	COPPER(Strings.STORAGE_COPPER, "Block of Copper", Ingot.COPPER),
	LEAD(Strings.STORAGE_LEAD, "Block of Lead", Ingot.LEAD),
	LODESTONE(Strings.STORAGE_LODESTONE, "Block of Lodestone", Ingot.LODESTONE),
	NICKEL(Strings.STORAGE_NICKEL, "Block of Nickel", Ingot.NICKEL),
	TELLURIUM(Strings.STORAGE_TELLURIUM, "Block of Tellurium", Ingot.TELLURIUM),
	TUNGSTEN(Strings.STORAGE_TUNGSTEN, "Block of Tungsten", Ingot.TUNGSTEN);
	
	public String unlocalizedName;
	public String localizedName;
	
	public Ingot ingot;
	
	private Storage(String unlocalizedName, String localizedName, Ingot ingot) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.ingot = ingot;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "storage/" + unlocalizedName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_STORAGE_ID, 1, this.ordinal());
	}
	
	public static Storage get(int ordinal) {
		return Storage.values()[ordinal];
	}
	
}
