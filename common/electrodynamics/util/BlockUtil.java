package electrodynamics.util;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BlockUtil {

	public static void dropItemFromBlock(World world, int x, int y, int z, ItemStack stack, Random random) {
		if (stack != null) {
			float f = random.nextFloat() * 0.9F + 0.1F;
			float f1 = random.nextFloat() * 0.9F + 0.1F;
			float f2 = random.nextFloat() * 0.9F + 0.1F;
			EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + 1.1F + f1), (double) ((float) z + f2), stack);
			
			float f3 = 0.05F;
			entityitem.motionX = (double) ((float) random.nextGaussian() * f3);
			entityitem.motionY = (double) ((float) random.nextGaussian() * f3 + 0.1F);
			entityitem.motionZ = (double) ((float) random.nextGaussian() * f3);
			if (stack.hasTagCompound()) {
				entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
			}

			world.spawnEntityInWorld(entityitem);
		}
	}

}
