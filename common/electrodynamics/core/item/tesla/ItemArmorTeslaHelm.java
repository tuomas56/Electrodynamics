package electrodynamics.core.item.tesla;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.configuration.ConfigurationSettings;
import electrodynamics.core.control.IKeyBoundClient;
import electrodynamics.core.core.CreativeTabED;
import electrodynamics.core.item.ItemHandler;
import electrodynamics.core.lib.ModInfo;

public class ItemArmorTeslaHelm extends ItemArmor implements IKeyBoundClient {

	private Icon texture;
	
	@SideOnly(Side.CLIENT)
	public boolean thermalEnabled = false;
	
	public ItemArmorTeslaHelm(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 0);
		setCreativeTab(CreativeTabED.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return stack.getItem() == ItemHandler.itemTeslaHelm ? ModInfo.RESOURCES_BASE + "/armor/tesla_1.png" : null;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/helmet");
	}

	@Override
	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key) {
		if (key.equals(ConfigurationSettings.THERMAL_VIEW_TOGGLE_NAME)) {
			this.thermalEnabled = !thermalEnabled;
			player.addChatMessage("Thermal View: " + (thermalEnabled == true ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF"));
		}
	}
	
}
