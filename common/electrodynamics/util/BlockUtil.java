package electrodynamics.util;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class BlockUtil {

	public static void dropItemFromBlock(World world, int x, int y, int z, ItemStack stack, Random random) {
		if (stack != null) {
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			EntityItem entityitem;

			for (float f2 = random.nextFloat() * 0.8F + 0.1F; stack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
				int k1 = random.nextInt(21) + 10;

				if (k1 > stack.stackSize) {
					k1 = stack.stackSize;
				}

				stack.stackSize -= k1;
				entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(stack.itemID, k1, stack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (double) ((float) random.nextGaussian() * f3);
				entityitem.motionY = (double) ((float) random.nextGaussian() * f3 + 0.2F);
				entityitem.motionZ = (double) ((float) random.nextGaussian() * f3);

				if (stack.hasTagCompound()) {
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
				}
			}
		}

		world.func_96440_m(x, y, z, world.getBlockId(x, y, z));
	}
	
}
