package electrodynamics.tileentity.machine.utilty;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.oredict.OreDictionary;
import electrodynamics.lib.block.Ore;
import electrodynamics.tileentity.machine.TileEntityMachine;

public class TileEntityConverter extends TileEntityMachine {

	@SuppressWarnings("unchecked")
	@Override
	public void updateEntityServer() {
		List<EntityItem> items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, getAABB());
		if (items != null && items.size() > 0) {
			EntityItem item = items.get(0);
			ItemStack stack = item.getEntityItem();
			
			if (stack != null) {
				// Temp
				item.setEntityItemStack(new ItemStack(Item.appleGold));
				
//				int oreID = OreDictionary.getOreID(stack);
//				
//				if (oreID != -1) {
//					// TODO Make this configurable
//					ItemStack newStack = OreDictionary.getOres(oreID).get(0);
//					newStack.stackSize = stack.stackSize;
//					
//					item.setEntityItemStack(newStack);
//				}
			}
		}
	}
	
	public AxisAlignedBB getAABB() {
		AxisAlignedBB aabb = AxisAlignedBB.getAABBPool().getAABB(xCoord + .5, yCoord + .5, zCoord + .5, xCoord + .5, yCoord + .5, zCoord + .5);
		aabb.expand(0, .5, 0);
		switch(this.rotation) {
		case NORTH: {
			aabb.expand(0, 0, .5);
			break;
		}
		case SOUTH: {
			aabb.expand(0, 0, .5);
			break;
		}
		case WEST: {
			aabb.expand(.5, 0, 0);
			break;
		}
		case EAST: {
			aabb.expand(.5, 0, 0);
			break;
		}
		default: break;
		}
		return aabb;
	}
	
}
