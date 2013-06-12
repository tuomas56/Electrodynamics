package electrodynamics.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidStack;

public class LiquidUtil {

	public static LiquidStack readLiquidFromNBT(String tagName, NBTTagCompound nbt) {
		NBTTagCompound liquidNBT = nbt.getCompoundTag(tagName);
		
		if (liquidNBT != null) {
			int id = liquidNBT.getInteger("itemID");
			int meta = liquidNBT.getInteger("itemMeta");
			int amount = liquidNBT.getInteger("amount");
			NBTTagCompound extra = liquidNBT.getCompoundTag("extra");
			
			LiquidStack liquid = new LiquidStack(id, amount, meta, extra);
			
			return liquid;
		}
		
		return null;
	}
	
	public static void writeLiquidToNBT(String tagName, NBTTagCompound nbt, LiquidStack liquid) {
		nbt.setTag(tagName, writeLiquidToNBT(liquid));
	}
	
	public static NBTTagCompound writeLiquidToNBT(LiquidStack liquid) {
		NBTTagCompound nbt = new NBTTagCompound();
		
		if (liquid != null) {
			nbt.setInteger("itemID", liquid.itemID);
			nbt.setInteger("itemMeta", liquid.itemMeta);
			nbt.setInteger("amount", liquid.amount);
			
			if (liquid.extra != null) {
				nbt.setTag("extra", liquid.extra);
			}
		}
		
		return nbt;
	}
	
	public static boolean areSameLiquid(LiquidStack ls1, LiquidStack ls2) {
		return ls1.itemID == ls2.itemID && ls1.itemMeta == ls2.itemMeta;
	}
	
	public static boolean canStoreLiquid(ILiquidTank tank, LiquidStack liquid) {
		if (tank.getLiquid() == null && liquid.amount <= tank.getCapacity()) return true;
		if (areSameLiquid(tank.getLiquid(), liquid) && tank.getLiquid().amount + liquid.amount <= tank.getCapacity()) return true;
		return false;
	}
	
}
