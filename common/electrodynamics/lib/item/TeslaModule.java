package electrodynamics.lib.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import electrodynamics.api.tool.IElMagLogic;
import electrodynamics.item.elmag.ItemElMagArmor;
import electrodynamics.item.elmag.logic.ElMagLogicMagneticPull;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.util.InventoryUtil;

public enum TeslaModule {

	MAGNETIC_PULL(Strings.MODULE_MAGNETIC_PULL, Strings.MODULE_MAGNETIC_PULL_DESC, new int[] {1}, new ElMagLogicMagneticPull(), "magneticPull"),
	XRAY(Strings.MODULE_XRAY, Strings.MODULE_XRAY_DESC, new int[] {0}, null, "moduleXray");
	
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

	public boolean hasModule(EntityPlayer player) {
		for (ItemStack armor : player.inventory.armorInventory) {
			if (armor != null && armor.getItem() instanceof ItemElMagArmor) {
				if (InventoryUtil.contains((((ItemElMagArmor)armor.getItem()).getInventory(armor).inventory), this.toItemStack())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean hasLogic() {
		return this.teslaLogic != null;
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
