package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Gas {

	AIR_CLEAN(Strings.GAS_AIR_CLEAN, "gasAirClean"),
	AIR_DIRTY(Strings.GAS_AIR_DIRTY, "gasAirDirty"),
	CO2(Strings.GAS_CO2, "gasCarbonDioxide"),
	H2S(Strings.GAS_H2S, "gasHydrogenSulfide"),
	METHANE(Strings.GAS_METHANE, "gasMethane"),
	COW_METHANE(Strings.GAS_COW_METHANE, "gasMooshroomFarts"),
	NATURAL_REFINED(Strings.GAS_NATURAL_REFINED, "gasNaturalRefined"),
	NATURAL_UNREFINED(Strings.GAS_NATURAL_UNREFINED, "gasNaturalUnrefined"),
	N(Strings.GAS_N, "gasNitrogen"),
	SO2(Strings.GAS_SO2, "gasSulfurDioxide"),
	H2SO4(Strings.GAS_H2SO4, "gasSulfuricAcid");
	
	public String unlocalizedName;
	public String textureFile;
	
	private Gas(String unlocalizedName, String textureFile) {
		this.unlocalizedName = unlocalizedName;
		this.textureFile = textureFile;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "gas/" + textureFile;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_GAS_ID, 1, this.ordinal());
	}
	
	public static Gas get(int ordinal) {
		return Gas.values()[ordinal];
	}
	
}
