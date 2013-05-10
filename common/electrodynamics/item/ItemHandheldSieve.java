package electrodynamics.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import electrodynamics.api.tool.ITool;
import electrodynamics.api.tool.ToolType;
import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;

public class ItemHandheldSieve extends Item implements ITool {
 
	private Icon texture;
	
	public ItemHandheldSieve(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/handSieveWood");
	}
	
	@Override
	public ToolType getToolType() {
		return ToolType.SIEVE;
	}

	@Override
	public void onToolUsed(ItemStack stack, World world, int x, int y, int z, EntityPlayer player) {
		stack.damageItem(1, player);
	}
	
}
