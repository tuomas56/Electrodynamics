package electrodynamics.tileentity.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import electrodynamics.core.CoreUtils;
import electrodynamics.core.misc.DamageSourceBlock;
import electrodynamics.lib.block.StructureComponent;
import electrodynamics.recipe.RecipeGrinder;
import electrodynamics.recipe.manager.CraftingManager;
import electrodynamics.util.EntityReflectionWrapper;
import electrodynamics.util.InventoryUtil;
import electrodynamics.util.LiquidUtil;
import electrodynamics.util.PlayerUtil;

public class TileEntityMobGrinder extends TileEntityStructure implements ITankContainer {

	public List<ItemStack> inventory = new ArrayList<ItemStack>();
	
	public LiquidTank tank = new LiquidTank(LiquidContainerRegistry.BUCKET_VOLUME);
	
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
		List<EntityLiving> entities = this.worldObj.getEntitiesWithinAABB(Entity.class, getCollectionAABB());
		
		if (entities != null && entities.size() > 0) {
			for (Entity entity : entities) {
				if (entity instanceof EntityLiving) {
					collectDropsFromEntity((EntityLiving) entity);
					entity.attackEntityFrom(new DamageSourceBlock(), ((EntityLiving)entity).getHealth());
				} else if (entity instanceof EntityItem) {
					if (collectItem(((EntityItem)entity).getEntityItem())) {
						entity.setDead();
					}
				}
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
	
	public boolean collectItem(ItemStack stack) {
		RecipeGrinder recipe = CraftingManager.getInstance().grindManager.getRecipe(stack);
		
		if (recipe != null) {
			for (ItemStack stackOut : recipe.itemOutput) {
				if (stackOut != null) {
					this.inventory.add(stackOut.copy());
				}
			}
			
			if (recipe.liquidOutput != null) {
				if (LiquidUtil.canStoreLiquid(tank, recipe.liquidOutput)) {
					tank.fill(recipe.liquidOutput, true);
				} else {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public void dispenseItem(ItemStack stack) {
		TileEntityStructure output = getOutputBlock();
		
		if (output != null) {
			if (this.inventory.remove(stack)) {
				int meta = this.worldObj.getBlockMetadata(output.xCoord, output.yCoord, output.zCoord);
				InventoryUtil.dispenseOutSide(worldObj, output.xCoord, output.yCoord, output.zCoord, ForgeDirection.getOrientation(meta), stack.copy(), new Random());
			}
		}
	}
	
	public TileEntityStructure getOutputBlock() {
		for (int y = yCoord; y < this.yCoord + 2; y++) {
			for (int x = xCoord - 1; x < xCoord + 2; x++) {
				for (int z = zCoord - 1; z < zCoord + 2; z++) {
					TileEntity tile = this.worldObj.getBlockTileEntity(x, y, z);

					if (tile != null && tile instanceof TileEntityStructure) {
						if (StructureComponent.values()[((TileEntityStructure)tile).getSubBlock()] == StructureComponent.VALVE) {
							return (TileEntityStructure) tile;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	private AxisAlignedBB getCollectionAABB() {
		double x = this.xCoord + .5;
		double y = this.yCoord + 1;
		double z = this.zCoord + .5;
		
		return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x, y, z).expand(.75, 1, .75);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.inventory = Arrays.asList(InventoryUtil.readItemsFromNBT("Items", nbt));
		this.tank.setLiquid(LiquidUtil.readLiquidFromNBT("Liquid", nbt));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		InventoryUtil.writeItemsToNBT("Items", nbt, (ItemStack[]) this.inventory.toArray());
		LiquidUtil.writeLiquidToNBT("Liquid", nbt, tank.getLiquid());
	}
	
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

	/* ITANKCONTAINER */
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return fill(0, resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return this.tank.fill(resource, doFill);
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return drain(0, maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] {this.tank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return getTanks(direction)[0];
	}

}
