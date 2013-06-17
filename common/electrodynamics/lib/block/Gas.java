package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Gas {

//	AIR_CLEAN(Strings.GAS_AIR_CLEAN, "gasAirClean"),
//	AIR_DIRTY(Strings.GAS_AIR_DIRTY, "gasAirDirty"),
	CO2(Strings.GAS_CO2, "gasCarbonDioxide", BlockIDs.BLOCK_GAS_CO2_ID),
//	H2S(Strings.GAS_H2S, "gasHydrogenSulfide"),
//	METHANE(Strings.GAS_METHANE, "gasMethane"),
//	COW_METHANE(Strings.GAS_COW_METHANE, "gasMooshroomFarts"),
//	NATURAL_REFINED(Strings.GAS_NATURAL_REFINED, "gasNaturalRefined"),
	NATURAL_UNREFINED(Strings.GAS_NATURAL_UNREFINED, "gasNaturalUnrefined", BlockIDs.BLOCK_GAS_NATURAL_ID),
	N(Strings.GAS_N, "gasNitrogen", BlockIDs.BLOCK_GAS_NITROGEN_ID),
//	SO2(Strings.GAS_SO2, "gasSulfurDioxide"),
//	H2SO4(Strings.GAS_H2SO4, "gasSulfuricAcid");
	;
	
	public String unlocalizedName;
	public String textureFile;
	
	public int id;
	
	private Gas(String unlocalizedName, String textureFile, int id) {
		this.unlocalizedName = unlocalizedName;
		this.textureFile = textureFile;
		this.id = id;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "gas/" + textureFile;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(this.id, 1, 0);
	}
	
	public static Gas get(int ordinal) {
		return Gas.values()[ordinal];
	}
	
}
