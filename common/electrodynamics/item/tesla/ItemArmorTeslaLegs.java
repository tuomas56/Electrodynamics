package electrodynamics.item.tesla;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.item.EDItems;
import electrodynamics.lib.core.ModInfo;

public class ItemArmorTeslaLegs extends ItemArmor {

	private Icon texture;
	
	public ItemArmorTeslaLegs(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 2);
		setCreativeTab(CreativeTabED.tool);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		Item thisArmor = EDItems.itemTeslaHelm;
		return stack.getItem() == thisArmor ? ModInfo.RESOURCES_BASE + "/armor/tesla_2.png" : null;
	}
	
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		player.motionX = player.motionX * 1D;
		player.motionZ = player.motionZ * 1D;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/leggings");
	}
	
}
