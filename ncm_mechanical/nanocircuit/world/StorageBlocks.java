package nanocircuit.world;


import nanocircuit.NanoCircuitWorld;
import net.minecraft.item.ItemStack;

public enum StorageBlocks {
	LODESTONE( "strgLodestone", "Lodestone Block" );

	private String unlocalizedName;
	private String localizedName;

	private StorageBlocks(String unlocalizedName, String localizedName) {
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
		return new ItemStack( NanoCircuitWorld.blockStorage, 1, this.ordinal() );
	}

	public static StorageBlocks get(int index) {
		return StorageBlocks.values()[index];
	}
}
