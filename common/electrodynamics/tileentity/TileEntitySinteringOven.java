package electrodynamics.tileentity;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.CoreUtils;
import electrodynamics.item.EDItems;
import electrodynamics.network.PacketUtils;
import electrodynamics.network.packet.PacketSound;
import electrodynamics.util.ItemUtil;

public class TileEntitySinteringOven extends TileEntityMachine {

	public final int ROTATIONAL_MAX = 2;
	
	public float doorAngle = 0;
	
	/** Does the oven currently have a tray in it. Primarily used for logic purposes */
	public boolean hasTray = false;
	
	public boolean open = false;
	
	/** Based off of furnace fuel burn time, but constantly drained "to keep oven hot" */
	public int fuelLevel;
	
	/** Set to the recipes value when tray is input. If this is greater than zero, a valid recipe is present */
	public int totalCookTime;
	
	/** Set to the recipes value when tray is input, when equal to zero, tray contents are replaced with recipe output */
	public int currentCookTime;
	
	/** Tray's internal inventory */
	public ItemStack[] trayInventory;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (CoreUtils.isServer(worldObj)) {
			if (fuelLevel > 0) {
				--this.fuelLevel;
			}
		}
		
		if (CoreUtils.isClient(worldObj)) {
			if (open && doorAngle <= ROTATIONAL_MAX) {
				doorAngle += 0.2F;
			} else if (!open && doorAngle > 0) {
				doorAngle -= 0.2F;
			}
			
			if (doorAngle < 0) {
				doorAngle = 0;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EntityLiving> getEntitiesInFireRange() {
		return this.worldObj.getEntitiesWithinAABB(EntityLiving.class, getFireDetectionBoundingBox());
	}
	
	public AxisAlignedBB getFireDetectionBoundingBox() {
		return this.getRenderBoundingBox().expand(1, 1, 1);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("open", open);
		nbt.setBoolean("hasTray", hasTray);
		nbt.setInteger("fuelLevel", fuelLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.open = nbt.getBoolean("open");
		this.hasTray = nbt.getBoolean("hasTray");
		this.fuelLevel = nbt.getInteger("fuelLevel");
	}
	
	@Override
	public void onBlockActivated(EntityPlayer player) {
		if (this.open) {
			if (player.getCurrentEquippedItem() != null) {
				if (player.getCurrentEquippedItem().getItem() == EDItems.itemTray) {
					//TODO Add held tray
					this.hasTray = true;
				} else if (ItemUtil.getFuelValue(player.getCurrentEquippedItem()) > 0) {
					this.fuelLevel += ItemUtil.getFuelValue(player.getCurrentEquippedItem());
					--player.getCurrentEquippedItem().stackSize;
					
					sendUpdatePacket(Side.CLIENT);
					return;
				}
			} else if (this.trayInventory != null && player.isSneaking()) {
				//Remove held tray
				
				this.trayInventory = null;
				this.hasTray = false;
				this.currentCookTime = 0;
				this.totalCookTime = 0;
				
				sendUpdatePacket(Side.CLIENT);
				return;
			}
		}
		
		open = !open;
		if (open == true && this.fuelLevel > 0) {
			PacketUtils.sendToPlayers(new PacketSound("1009", xCoord, yCoord, zCoord, PacketSound.TYPE_SFX).makePacket(), this);
		}
		sendUpdatePacket(Side.CLIENT);
	}

}
