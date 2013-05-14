package electrodynamics.lib.item;

import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import net.minecraft.item.ItemStack;

public enum Component {

	FAN_BLADE(Strings.COMPONENT_FAN_BLADE, "fanBlade"), 
	MAGNET(Strings.COMPONENT_MAGNET, "magnet"),
	MAGNETITE_CHUNK(Strings.COMPONENT_MAGNETITE_CHUNK, "magnetiteChunk"),
	METAL_BAR(Strings.COMPONENT_METAL_BAR, "metalBar"),
	ARSENIC(Strings.COMPONENT_ARSENIC, "arsenic"),
	LODESTONE_PEBBLE(Strings.COMPONENT_LODESTONE_PEBBLE, "lodestonePebble"),
	SAP(Strings.COMPONENT_SAP, "sap"),
	TWINE(Strings.COMPONENT_TWINE, "twine"),
	TWINE_MESH(Strings.COMPONENT_TWINE_MESH, "twineMesh"),
	LITHIUM_CLAY(Strings.COMPONENT_LITHIUM_CLAY_DRY, "lithiumClayDry"),
	LITHIUM_CLAY_WET(Strings.COMPONENT_LITHIUM_CLAY_WET, "lithiumClayWet"),
	WORMWOOD_LEAF(Strings.COMPONENT_WORMWOOD_LEAF, "wormwoodLeaf"),
	PCB(Strings.COMPONENT_PCB, "pcb"),
	VOIDSTONE_MAGNET(Strings.COMPONENT_VOIDSTONE_MAGNET, "voidstoneMagnet"),
	OVEN_WALL(Strings.COMPONENT_OVEN_WALL, "ovenWall");
	
	public String unlocalizedName;
	private String textureName;

	private Component(String unlocalizedName, String textureName) {
		this.unlocalizedName = unlocalizedName;
		this.textureName = textureName;
	}

	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "component/" + textureName;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public String getLocalizedName(String language) {
		return "";
	}

	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_COMPONENT_ID + 256, 1, this.ordinal());
	}

	public static Component get(int ordinal) {
		return Component.values()[ordinal];
	}

}
