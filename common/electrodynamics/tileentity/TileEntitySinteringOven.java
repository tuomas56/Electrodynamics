package electrodynamics.tileentity;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.CoreUtils;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketUtils;
import electrodynamics.network.Payload;
import electrodynamics.network.packet.PacketPayload;
import electrodynamics.network.packet.PacketSound;
import electrodynamics.util.ItemUtil;

public class TileEntitySinteringOven extends TileEntityMachine implements IPayloadReceiver {

	public final int ROTATIONAL_MAX = 2;
	
	public float doorAngle = 0;
	
	public boolean open = false;
	
	/** Based off of furnace fuel burn time, but constantly drained "to keep oven hot" */
	public int fuelLevel;
	
	/** Set to the recipes value when tray is input, when equal to zero, tray contents are replaced with recipe output */
	public int currentCookTime;
	
	/** Instance of the tray that's in the furnace. */
	public ItemStack trayItem;
	
	/** Array of items in said tray */
	public ItemStack[] trayContents;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (CoreUtils.isServer(worldObj)) {
			if (fuelLevel > 0) {
				--this.fuelLevel;
			}
			
			if (this.open) {
				List<EntityLiving> entities = getEntitiesInFireRange();
				
				if (entities != null && entities.size() > 0) {
					for (EntityLiving entity : entities) {
						entity.setFire(10);
					}
				}
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
	public void handlePayload(Payload payload) {
		this.open = payload.boolPayload[0];
		this.rotation = ForgeDirection.getOrientation(payload.bytePayload[0]);
		this.fuelLevel = payload.intPayload[0];
	}
	
	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(1, 1, 1, 0, 0);
		payload.boolPayload[0] = this.open;
		payload.bytePayload[0] = (byte) this.rotation.ordinal();
		payload.intPayload[0] = this.fuelLevel;
		PacketPayload packet = new PacketPayload(xCoord, yCoord, zCoord, payload);
		return packet.makePacket();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("open", open);
		nbt.setInteger("fuelLevel", fuelLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.open = nbt.getBoolean("open");
		this.fuelLevel = nbt.getInteger("fuelLevel");
	}
	
	@Override
	public void onBlockActivated(EntityPlayer player) {
		if (open) {
			if (player.getCurrentEquippedItem() != null) {
				if (ItemUtil.getFuelValue(player.getCurrentEquippedItem()) > 0) {
					this.fuelLevel += ItemUtil.getFuelValue(player.getCurrentEquippedItem());
					--player.getCurrentEquippedItem().stackSize;
				}
			}
		} else {
			open = !open;
			
			if (open == true && this.fuelLevel > 0) {
				PacketUtils.sendToPlayers(new PacketSound("1009", xCoord, yCoord, zCoord, PacketSound.TYPE_SFX).makePacket(), this);
			}
		}
		
		sendUpdatePacket(Side.CLIENT);
	}

}
