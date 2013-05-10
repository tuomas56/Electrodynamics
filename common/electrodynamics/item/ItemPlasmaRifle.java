package electrodynamics.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import electrodynamics.core.CreativeTabED;
import electrodynamics.entity.EntityPlasmaBeam;

public class ItemPlasmaRifle extends Item {

	public ItemPlasmaRifle(int id) {
		super(id);
		setCreativeTab(CreativeTabED.tool);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		EntityPlasmaBeam beam = new EntityPlasmaBeam(world, player.posX, player.posY, player.posZ, 200);
		beam.setRGB(255, 0, 0);
		world.spawnEntityInWorld(beam);
	
		return stack;
	}
	
}
