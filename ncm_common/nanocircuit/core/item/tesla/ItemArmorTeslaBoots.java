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

public class ItemArmorTeslaBoots extends ItemArmor {

	private Icon texture;
	
	public ItemArmorTeslaBoots(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 3);
		setCreativeTab(CreativeTabNCM.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return stack.getItem() == ItemHandler.itemTeslaBoots ? ModInfo.RESOURCES_BASE + "/armor/tesla_2.png" : null;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/itemArmorTeslaBoots");
	}
	
}
