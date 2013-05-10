package electrodynamics.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.util.PlayerUtil;

public class ItemHandheldSieve extends Item {
 
	public ItemHandheldSieve(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(500);
		setCreativeTab(CreativeTabED.tool);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		Entity clickedEntity = PlayerUtil.getPointedEntity(world, player, 5, 0);
	
		if (clickedEntity != null) {
			System.out.println("right clicked entity");
			
			if (clickedEntity instanceof EntityItem) {
				System.out.println("right clicked item");
			}
		}
		
		return stack;
	}
	
}
