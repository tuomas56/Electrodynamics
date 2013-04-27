package nanocircuit.world.lib;

import net.minecraft.item.ItemStack;

public enum StorageBlock {
	LODESTONE( "strgLodestone", "Lodestone Block" );

	private String unlocalizedName;
	private String localizedName;

	private StorageBlock(String unlocalizedName, String localizedName) {
		this.unlocalizedName = unlocalizedName;
		this.localizedName = localizedName; // temporary
	}

	public String getUnlocalizedName() {
		return "tile." + unlocalizedName + ".name";
	}

	public String getLocalizedName(String language) {
		return localizedName;
	}

	public String getTextureFile() {
		return "NCWorld:" + unlocalizedName;
	}

	public ItemStack toItemStack() {
		//TODO Rewrite - dmillerw
//		return new ItemStack( NanoCircuitWorld.blockStorage, 1, this.ordinal() );
		return null;
	}

	public static StorageBlock get(int index) {
		return StorageBlock.values()[index];
	}
}
