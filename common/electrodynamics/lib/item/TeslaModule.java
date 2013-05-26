package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import electrodynamics.api.tool.IElMagLogic;
import electrodynamics.item.elmag.logic.ElMagLogicMagneticPull;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum TeslaModule {

	MAGNETIC_PULL(Strings.MODULE_MAGNETIC_PULL, Strings.MODULE_MAGNETIC_PULL_DESC, new int[] {1}, new ElMagLogicMagneticPull(), "magneticPull");
	
	public String unlocalizedName;
	public String moduleDescription;
	public int[] validArmorTypes;
	public IElMagLogic teslaLogic;
	private String textureName;

	private TeslaModule(String unlocalizedName, String moduleDescription, int[] validArmorTypes, IElMagLogic teslaLogic, String textureName) {
		this.unlocalizedName = unlocalizedName;
		this.moduleDescription = moduleDescription;
		this.validArmorTypes = validArmorTypes;
		this.teslaLogic = teslaLogic;
		this.textureName = textureName;
	}

	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "tesla/module/" + textureName;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public String getLocalizedName(String language) {
		return "";
	}

	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_ELMAG_MODULE_ID + 256, 1, this.ordinal());
	}

	public static TeslaModule get(int ordinal) {
		return TeslaModule.values()[ordinal];
	}
	
}
