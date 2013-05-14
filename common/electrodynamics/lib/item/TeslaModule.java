package electrodynamics.lib.item;

import net.minecraft.item.ItemStack;
import electrodynamics.api.tool.ITeslaLogic;
import electrodynamics.item.tesla.logic.TeslaLogicMagneticPull;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum TeslaModule {

	MAGNETIC_PULL(Strings.MODULE_MAGNETIC_PULL, Strings.MODULE_MAGNETIC_PULL_DESC, new int[] {1}, new TeslaLogicMagneticPull(), "magneticPull");
	
	public String unlocalizedName;
	public String moduleDescription;
	public int[] validArmorTypes;
	public ITeslaLogic teslaLogic;
	private String textureName;

	private TeslaModule(String unlocalizedName, String moduleDescription, int[] validArmorTypes, ITeslaLogic teslaLogic, String textureName) {
		this.unlocalizedName = unlocalizedName;
		this.moduleDescription = moduleDescription;
		this.validArmorTypes = validArmorTypes;
		this.teslaLogic = teslaLogic;
		this.textureName = textureName;
	}

	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "module/" + textureName;
	}

	public String getUnlocalizedName() {
		return unlocalizedName;
	}

	public String getLocalizedName(String language) {
		return "";
	}

	public ItemStack toItemStack() {
		return new ItemStack(ItemIDs.ITEM_TESLA_MODULE_ID + 256, 1, this.ordinal());
	}

	public static TeslaModule get(int ordinal) {
		return TeslaModule.values()[ordinal];
	}
	
}
