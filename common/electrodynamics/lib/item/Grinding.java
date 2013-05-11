package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Grinding {

	//Mod Ores
	CHALCOPYRITE(Strings.GROUND_CHALCOPYRITE, "grindingsChalcopyrite"),
	COBALTITE(Strings.GROUND_COBALTITE, "grindingsCobaltite"),
	GALENA(Strings.GROUND_GALENA, "grindingsGalena"),
	MAGNETITE(Strings.GROUND_MAGNETITE, "grindingsMagnetite"),
	NICKEL(Strings.GROUND_NICKEL, "grindingsNickel"),
	WOLFRAMITE(Strings.GROUND_WOLFRAMITE, "grindingsWolframite"),
	VOIDSTONE(Strings.GROUND_VOIDSTONE, "grindingsVoidstone"),
	BLUESTONE(Strings.GROUND_BLUESTONE, "grindingsBluestone"),
	GREENSTONE(Strings.GROUND_GREENSTONE, "grindingsGreenstone"),
	LITHIUM(Strings.GROUND_LITHIUM, "grindingsLithium"),
	DIAMOND(Strings.GROUND_DIAMOND, "grindingsDiamond"),
	EMERALD(Strings.GROUND_EMERALD, "grindingsEmerald"), 
	GOLD(Strings.GROUND_GOLD, "grindingsGold"), 
	IRON(Strings.GROUND_IRON, "grindingsIron"), 
	LAPIS(Strings.GROUND_LAPIS, "grindingsLapis"),
	REDSTONE(Strings.GROUND_REDSTONE, "grindingsRedstone");
	
	public String unlocalizedName;
	public String textureFile;
	
	private Grinding(String unlocalizedName, String textureFile) {
		this.unlocalizedName = unlocalizedName;
		this.textureFile = textureFile;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "dust/ground/" + textureFile;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_DUST_ID + 256, 1, this.ordinal() + Dust.values().length);
	}
	
	public static Grinding get(int ordinal) {
		return Grinding.values()[ordinal];
	}
	
}
