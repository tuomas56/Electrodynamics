package nanocircuit.core.item;

import java.util.List;

import nanocircuit.core.core.CreativeTabNCM;
import nanocircuit.core.lib.Component;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemMagnetizedArmor extends Item {

	public ItemMagnetizedArmor(int id) {
		super(id);
		setCreativeTab(CreativeTabNCM.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return ItemHandler.itemComponent.getIconFromDamage(Component.MAGNET.ordinal());
	}
	
	public boolean isValidArmor(ItemStack stack, int armorType) {
		return (armorType == 1) && (stack.getItem() == ItemHandler.itemMagArmor);
	}
	
	@SuppressWarnings({ "unchecked" })
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;
		
		List<EntityItem> nearbyItems = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(2.5D, 0.5D, 2.5D));
		
		for (EntityItem item : nearbyItems) {
			if (item != null) {
				double d0 = 8.0D;
				double d1 = (player.posX - item.posX) / d0;
	            double d2 = (player.posY + (double)player.getEyeHeight() - item.posY) / d0;
	            double d3 = (player.posZ - item.posZ) / d0;
	            double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
	            double d5 = 1.0D - d4;

	            if (d5 > 0.0D) {
	                d5 *= d5;
	                item.motionX += d1 / d4 * d5 * 0.1D;
	                item.motionY += d2 / d4 * d5 * 0.1D;
	                item.motionZ += d3 / d4 * d5 * 0.1D;
	            }
			}
		}
    }
	
}
