package electrodynamics.item.hammer;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import electrodynamics.lib.core.ModInfo;

public class ItemSledgeHammer extends ItemHammer {

	private Icon texture;
	
	public ItemSledgeHammer(int id) {
		super(id, 500);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/sledgeHammer");
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		final int KNOCKBACK = 2;
		
		entity.addVelocity(-MathHelper.sin(player.rotationYaw * (float)Math.PI / 180.0F) * KNOCKBACK * 0.5F, 0.2D, MathHelper.cos(player.rotationYaw * (float)Math.PI / 180.0F) * KNOCKBACK * 0.5F);
		return false;
	}
	
	public int getDamageVsEntity(Entity entity) {
		return 10;
	}
	
}
