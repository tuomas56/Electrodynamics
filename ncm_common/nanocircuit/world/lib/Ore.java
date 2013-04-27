package nanocircuit.world.lib;

import nanocircuit.core.core.CoreConfiguration;
import nanocircuit.core.lib.Component;
import nanocircuit.core.lib.ModInfo;
import nanocircuit.world.core.WorldConfiguration;
import net.minecraft.item.ItemStack;

public enum Ore {

	MAGNETITE("oreMagnetite", "Magnetite Ore", CoreConfiguration.getShiftedItemID("itemComponent"), Component.valueOf("MAGNETITE_CHUNK").ordinal(), 1), 
	NICKEL("oreNickel", "Nickel Ore", CoreConfiguration.getBlockID("blockOre"), 1, 1);

	private String unlocalizedName;
	private String localizedName; // temporary
	
	public int harvestLevel = 2;
	public int droppedID;
	public int droppedMeta;
	public int droppedCount;

	private Ore(String unlocalizedName, String localizedName, int dropID, int dropMeta, int dropCount) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
		this.droppedID = dropID;
		this.droppedMeta = dropMeta;
		this.droppedCount = dropCount;
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
		return new ItemStack(WorldConfiguration.getBlockID("blockOre"), 1,
				this.ordinal());
	}

	public static Ore get(int ordinal) {
		return Ore.values()[ordinal];
	}

}
