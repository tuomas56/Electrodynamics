package electrodynamics.lib.block;

import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum Ore {

	CHALCOPYRITE( Strings.ORE_CHALCOPYRITE_NAME, "Chalcopyrite Ore" ),
	COBALTITE( Strings.ORE_COBALTITE_NAME, "Cobaltite Ore" ),
	GALENA( Strings.ORE_GALENA_ORE, "Galena Ore" ),
	MAGNETITE( Strings.ORE_MAGNETITE_NAME, "Magnetite Ore" ),
	NICKEL( Strings.ORE_NICKEL_NAME, "Nickel Ore" ),
	WOLFRAMITE( Strings.ORE_WOLFRAMITE_NAME, "Wolframite Ore" ),
	VOIDSTONE(Strings.ORE_VOIDSTONE, "Voidstone"),
	BLUESTONE(Strings.ORE_BLUESTONE, "Bluestone Ore"),
	GREENSTONE(Strings.ORE_GREENSTONE, "Greenstone Ore");

	private String unlocalizedName;
	private String localizedName; // temporary

	public int altDropID;
	public int altDropMeta;
	public int altDropCount;
	
	public String oreDictionaryName;

	public int harvestLevel = 2;

	private Ore(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.oreDictionaryName = unlocalizedName;
	}

	private Ore(String unlocalizedName, String localizedName, String alt) {
		this( unlocalizedName, localizedName );
		this.oreDictionaryName = alt;
	}

	private Ore(String unlocalizedName, String localizedName, int harvestLevel) {
		this( unlocalizedName, localizedName );
		this.harvestLevel = harvestLevel;
	}

	private Ore(String unlocalizedName, String localizedName, int harvestLevel, int dropID, int dropMeta, int dropCount) {
		this( unlocalizedName, localizedName );
		this.harvestLevel = harvestLevel;
		this.altDropID = dropID;
		this.altDropMeta = dropMeta;
		this.altDropCount = dropCount;
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "world/ore/" + unlocalizedName;
	}

	public String getUnlocalizedName() {
		return "tile." + unlocalizedName + ".name";
	}

	public String getLocalizedName(String language) {
		return localizedName; // temp
	}

	public ItemStack toItemStack() {
		return new ItemStack( BlockIDs.BLOCK_ORE_ID, 1, this.ordinal() );
	}

	public void registerWithOreDictionary() {
		OreDictionary.registerOre(unlocalizedName, this.toItemStack());
	}
	
	public static Ore get(int ordinal) {
		return Ore.values()[ordinal];
	}

}
