package electrodynamics.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.core.CoreUtils;
import electrodynamics.core.misc.DamageSourceBlock;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.util.EntityReflectionWrapper;
import electrodynamics.util.InventoryUtil;
import electrodynamics.util.PlayerUtil;

public class TileEntityMobGrinder extends TileStructure {

	public ArrayList<ItemStack> inventory = new ArrayList<ItemStack>();
	
	@Override
	public void updateEntity() {
		if (CoreUtils.isServer(worldObj)) {
			if (this.isValidStructure() && this.isCentralTileEntity()) {
				collectEntities();
				
				if (worldObj.getWorldTime() % 5 == 0) { // Runs four times per second
					if (inventory != null && inventory.size() > 0) {
						dispenseItem(inventory.get(inventory.size() - 1));
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void collectEntities() {
		List<EntityLiving> entities = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, getCollectionAABB());
		
		if (entities != null && entities.size() > 0) {
			for (EntityLiving entity : entities) {
				collectDropsFromEntity(entity);
				entity.attackEntityFrom(new DamageSourceBlock(), entity.getHealth());
			}
		}
	}
	
	private void collectDropsFromEntity(EntityLiving entity) {
		if (entity.getHealth() > 0) {
			if (!(entity instanceof EntityPlayer)) {
				EntityReflectionWrapper erw = new EntityReflectionWrapper(entity);
				int count = new Random().nextInt(3);

				for (int i=0; i<count; i++) {
					this.inventory.add(new ItemStack(erw.getMainDropID(), 1, 0));
				}
			} else {
				EntityPlayer player = (EntityPlayer) entity;
				
				if (!player.capabilities.isCreativeMode) {
					for (int i=0; i<player.inventory.mainInventory.length; i++) {
						ItemStack stack = player.inventory.getStackInSlot(i);
						
						if (stack != null) {
							this.inventory.add(stack.copy());
							player.inventory.setInventorySlotContents(i, null);
						}
					}
					
					for (int i=0; i<player.inventory.armorInventory.length; i++) {
						ItemStack stack = player.inventory.getStackInSlot(i);
						
						if (stack != null) {
							this.inventory.add(stack.copy());
							player.inventory.setInventorySlotContents(i, null);
						}
					}
					
//					if (new Random().nextInt(10) == 0) {
						this.inventory.add(PlayerUtil.getPlayerHead(player).copy());
//					}
				}
			}
		}
	}
	
	public void dispenseItem(ItemStack stack) {
		TileStructure output = getOutputBlock();
		
		if (output != null) {
			if (this.inventory.remove(stack)) {
				int meta = this.worldObj.getBlockMetadata(output.xCoord, output.yCoord, output.zCoord);
				InventoryUtil.dispenseOutSide(worldObj, output.xCoord, output.yCoord, output.zCoord, ForgeDirection.getOrientation(meta), stack.copy(), new Random());
			}
		}
	}
	
	public TileStructure getOutputBlock() {
		for (int y = yCoord; y < this.yCoord + 2; y++) {
			for (int x = xCoord - 1; x < xCoord + 2; x++) {
				for (int z = zCoord - 1; z < zCoord + 2; z++) {
					TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);

					if (tile != null && tile instanceof TileStructure) {
						if (StructureComponent.values()[((TileStructure)tile).getSubBlock()] == StructureComponent.MOB_GRINDER_OUTPUT) {
							return (TileStructure) tile;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private AxisAlignedBB getCollectionAABB() {
		double x = this.xCoord + .5;
		double y = this.yCoord + 2;
		double z = this.zCoord + .5;
		
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x, y, z).expand(1.25, 1, 1.25);
	}
	
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

}
