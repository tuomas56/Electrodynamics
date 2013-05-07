package electrodynamics.lib;

import net.minecraft.item.ItemStack;

public enum Ore {

	COBALTITE(Strings.ORE_COBALTITE_NAME, "Cobaltite Ore"),
	CHALCOPYRITE(Strings.ORE_CHALCOPYRITE_NAME, "Chalcopyrite Ore"),
	GALENA(Strings.ORE_GALENA_ORE, "Galena Ore"),
	LITHIUM_CLAY(Strings.ORE_LITHIUM_CLAY, "Lithium-Rich Clay"),
	MAGNETITE(Strings.ORE_MAGNETITE_NAME, "Magnetite Ore"), 
	NICKEL(Strings.ORE_NICKEL_NAME, "Nickel Ore"),
	WOLFRAMITE(Strings.ORE_WOLFRAMITE_NAME, "Wolframite Ore");

	private String unlocalizedName;
	private String localizedName; // temporary
	
	public String oreDictionaryName;
	
	public int harvestLevel = 2;
	
	private Ore(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.oreDictionaryName = unlocalizedName;
	}

	private Ore(String unlocalizedName, String localizedName, String alt) {
		this(unlocalizedName, localizedName);
		this.oreDictionaryName = alt;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "ore/" + unlocalizedName;
	}

	public String getUnlocalizedName() {
		return "tile." + unlocalizedName + ".name";
	}

	public String getLocalizedName(String language) {
		return localizedName; // temp
	}

	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_ORE_ID, 1, this.ordinal());
	}

	public static Ore get(int ordinal) {
		return Ore.values()[ordinal];
	}

}
