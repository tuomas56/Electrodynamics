package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Gas {

	AIR_CLEAN(Strings.GAS_AIR_CLEAN, "Clean Air"),
	AIR_DIRTY(Strings.GAS_AIR_DIRTY, "Dirty Air"),
	CO2(Strings.GAS_CO2, "Carbon Dioxide"),
	H2S(Strings.GAS_H2S, "Hydrogen Sulfide"),
	METHANE(Strings.GAS_METHANE, "Methane"),
	COW_METHANE(Strings.GAS_COW_METHANE, "Cow-Produced Methane"),
	NATURAL_REFINED(Strings.GAS_NATURAL_REFINED, "Refined Natural Gas"),
	NATURAL_UNREFINED(Strings.GAS_NATURAL_UNREFINED, "Unrefined Natural Gas"),
	N(Strings.GAS_N, "Nitrogen"),
	SO2(Strings.GAS_SO2, "Sulfur Dioxide"),
	H2SO4(Strings.GAS_H2SO4, "Sulfuric Acid");
	
	public String unlocalizedName;
	public String localizedName;
	
	private Gas(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "gas/" + unlocalizedName;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_GAS_ID, 1, this.ordinal());
	}
	
	public static Gas get(int ordinal) {
		return Gas.values()[ordinal];
	}
	
}
