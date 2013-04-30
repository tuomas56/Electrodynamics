package nanocircuit.core.item.tesla;

import nanocircuit.core.core.CreativeTabNCM;
import nanocircuit.core.item.ItemHandler;
import nanocircuit.core.lib.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemArmorTeslaHelm extends ItemArmor {

	private Icon texture;
	
	public ItemArmorTeslaHelm(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 0);
		setCreativeTab(CreativeTabNCM.item);
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
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/itemArmorTeslaHelmet");
	}
	
}
