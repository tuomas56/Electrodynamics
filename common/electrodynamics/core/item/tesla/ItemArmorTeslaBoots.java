package electrodynamics.core.item.tesla;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.core.core.CreativeTabED;
import electrodynamics.core.item.ItemHandler;
import electrodynamics.core.lib.ModInfo;

public class ItemArmorTeslaBoots extends ItemArmor {

	private Icon texture;
	
	public ItemArmorTeslaBoots(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 3);
		setCreativeTab(CreativeTabED.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return stack.getItem() == ItemHandler.itemTeslaBoots ? ModInfo.RESOURCES_BASE + "/armor/tesla_1.png" : null;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/boots");
	}

}
