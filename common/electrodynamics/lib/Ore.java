package electrodynamics.lib;

import net.minecraft.item.ItemStack;

public enum Ore {

	MAGNETITE(Strings.ORE_MAGNETITE_NAME, "Magnetite Ore", BlockIDs.BLOCK_ORE_ID, 0, 1, ItemIDs.ITEM_COMPONENT_ID, Component.MAGNETITE_DUST.ordinal(), 1), 
	NICKEL(Strings.ORE_NICKEL_NAME, "Nickel Ore", BlockIDs.BLOCK_ORE_ID, 1, 1, BlockIDs.BLOCK_ORE_ID, 1, 1);

	private String unlocalizedName;
	private String localizedName; // temporary
	
	public String oreDictionaryName;
	
	public int harvestLevel = 2;
	
	public int droppedID;
	public int droppedMeta;
	public int droppedCount;

	public int altDroppedID;
	public int altDroppedMeta;
	public int altDroppedCount;
	
	private Ore(String unlocalizedName, String localizedName, int dropID, int dropMeta, int dropCount, int altID, int altMeta, int altCount) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.oreDictionaryName = unlocalizedName;
		this.droppedID = dropID;
		this.droppedMeta = dropMeta;
		this.droppedCount = dropCount;
		this.altDroppedID = altID;
		this.altDroppedMeta = altMeta;
		this.altDroppedCount = altCount;
	}

	private Ore(String unlocalizedName, String localizedName, String alt, int dropID, int dropMeta, int dropCount, int altID, int altMeta, int altCount) {
		this(unlocalizedName, localizedName, dropID, dropMeta, dropCount, altID, altMeta, altCount);
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
