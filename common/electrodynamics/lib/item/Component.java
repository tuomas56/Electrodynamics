package electrodynamics.lib.item;

import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import net.minecraft.item.ItemStack;

public enum Component {

	FAN_BLADE(Strings.FAN_BLADE_NAME, "Fan Blade"), 
	LODESTONE_INGOT(Strings.LODESTONE_INGOT_NAME, "Lodestone Ingot"),
	MAGNET(Strings.MAGNET_NAME, "Magnet"),
	MAGNETITE_CHUNK(Strings.MAGNETITE_CHUNK_NAME, "Magnetite Chunk"),
	MAGNETITE_DUST(Strings.MAGNETITE_DUST_NAME, "Magnetite Dust"),
	METAL_BAR(Strings.METAL_BAR, "Metal Bar"),
	ARSENIC(Strings.ARSENIC, "Arsenic"),
	LODESTONE_PEBBLE(Strings.LODESTONE_PEBBLE, "Lodestone Pebble"),
	SAP(Strings.SAP, "Sap"),
	TWINE(Strings.TWINE, "Piece of Twine"),
	LITHIUM_CLAY(Strings.LITHIUM_CLAY, "Dried Lithium Clay"),
	WORMWOOD_LEAF(Strings.WORMWOOD_LEAF, "Wormwood Leaf"),
	PCB(Strings.PCB, "PCB"),
	VOIDSTONE_MAGNET(Strings.VOIDSTONE_MAGNET, "Voidstone Magnet");
	
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
		return new ItemStack(ItemIDs.ITEM_COMPONENT_ID + 256, 1, this.ordinal());
	}

	public static Component get(int ordinal) {
		return Component.values()[ordinal];
	}

}
