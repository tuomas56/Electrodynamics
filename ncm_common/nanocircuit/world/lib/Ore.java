package nanocircuit.world.lib;

import nanocircuit.world.core.WorldConfiguration;
import net.minecraft.item.ItemStack;

public enum Ore {

	MAGNETITE( "oreMagnetite", "Magnetite Ore" ),
	NICKEL( "oreNickel", "Nickel Ore" );

	private String unlocalizedName;
	private String localizedName; // temporary
	private int harvestLevel = 2;

	private Ore(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}

	private Ore(String unlocalizedName, String localizedName, int harvestLevel) {
		this( unlocalizedName, localizedName );
		this.harvestLevel = harvestLevel;
	}

	public String getTextureFile() {
		return "NCWorld:" + unlocalizedName;
	}

	public String getUnlocalizedName() {
		return "tile." + unlocalizedName + ".name";
	}

	public String getLocalizedName(String language) {
		return localizedName; // temp
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public ItemStack toItemStack() {
		return new ItemStack(WorldConfiguration.getBlockID("blockOre"), 1, this.ordinal() );
	}

	public static Ore get(int ordinal) {
		return Ore.values()[ordinal];
	}

}
