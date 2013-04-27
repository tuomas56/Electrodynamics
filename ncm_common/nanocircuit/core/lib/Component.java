package nanocircuit.core.lib;

import nanocircuit.core.core.CoreConfiguration;
import net.minecraft.item.ItemStack;

public enum Component {

	FAN_BLADE("fanBlade", "Fan Blade"), 
	LODESTONE_DUST("lodestoneDust", "Lodestone Dust"),
	LODESTONE_INGOT("lodestoneIngot", "Lodestone Ingot"),
	MAGNET("magnet", "Magnet"),
	MAGNETITE_CHUNK("magnetiteChunk", "Magnetite Chunk"),
	MAGNETITE_DUST("magnetiteDust", "Magnetite Dust"),
	METAL_BAR("metalBar", "Metal Bar");

	private String unlocalizedName;
	private String localizedName; // temporary

	private Component(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName;
	}

	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "component/" + unlocalizedName;
	}

	public String getUnlocalizedName() {
		return "item." + unlocalizedName + ".name";
	}

	public String getLocalizedName(String language) {
		return localizedName; // temp
	}

	public ItemStack toItemStack() {
		return new ItemStack(CoreConfiguration.getShiftedItemID("itemComponent"), 1, this.ordinal());
	}

	public static Component get(int ordinal) {
		return Component.values()[ordinal];
	}

}
