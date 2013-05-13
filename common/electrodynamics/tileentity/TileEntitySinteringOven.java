package electrodynamics.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.core.CoreUtils;
import electrodynamics.inventory.InventoryItem;
import electrodynamics.item.EDItems;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketUtils;
import electrodynamics.network.Payload;
import electrodynamics.network.packet.PacketPayload;
import electrodynamics.network.packet.PacketSound;
import electrodynamics.recipe.CraftingManager;
import electrodynamics.recipe.RecipeSinteringOven;
import electrodynamics.util.BlockUtil;
import electrodynamics.util.ItemUtil;

public class TileEntitySinteringOven extends TileEntityMachine implements IPayloadReceiver {

	public final int ROTATIONAL_MAX = 2;
	
	public float doorAngle = 0;
	
	public boolean hasTray = false;
	
	public boolean open = false;
	
	/** Based off of furnace fuel burn time, but constantly drained "to keep oven hot" */
	public int fuelLevel;
	
	/** Set to the recipes value when tray is input. If this is greater than zero, a valid recipe is present */
	public int totalCookTime;
	
	/** Set to the recipes value when tray is input, when equal to zero, tray contents are replaced with recipe output */
	public int currentCookTime;
	
	/** Instance of tray's inventory */
	public InventoryItem trayInventory;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (CoreUtils.isServer(worldObj)) {
			if (fuelLevel > 0) {
				--this.fuelLevel;
				
				if (this.trayInventory != null) {
					if (totalCookTime > 0) {
						if (currentCookTime == 0) {
							ArrayList<ItemStack> trayInventoryList = new ArrayList<ItemStack>(Arrays.asList(trayInventory.inventory));
							
							if (trayInventoryList != null) {
								RecipeSinteringOven recipe = CraftingManager.getInstance().ovenManager.getRecipe(trayInventoryList);
								
								if (recipe != null) {
									trayInventory.inventory = new ItemStack[recipe.itemOutputs.size()];
									for (int i=0; i<recipe.itemOutputs.size(); i++) {
										if (recipe.itemOutputs.get(i) != null) {
											trayInventory.setInventorySlotContents(i, recipe.itemOutputs.get(i).copy());
										}
									}
								}
							}
						} else {
							--currentCookTime;
						}
					} else {
						ArrayList<ItemStack> trayInventoryList = new ArrayList<ItemStack>(Arrays.asList(trayInventory.inventory));
						
						if (trayInventoryList != null) {
							RecipeSinteringOven recipe = CraftingManager.getInstance().ovenManager.getRecipe(trayInventoryList);
							
							if (recipe != null) {
								this.totalCookTime = this.currentCookTime = recipe.processingTime;
							}
						}
					}
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
		this.hasTray = payload.boolPayload[1];
		this.rotation = ForgeDirection.getOrientation(payload.bytePayload[0]);
		this.fuelLevel = payload.intPayload[0];
		readFromNBT(payload.nbtPayload[0]);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		Payload payload = new Payload(2, 1, 1, 0, 0, 1);
		payload.boolPayload[0] = this.open;
		payload.boolPayload[1] = this.trayInventory != null;
		payload.bytePayload[0] = (byte) this.rotation.ordinal();
		payload.intPayload[0] = this.fuelLevel;
		payload.nbtPayload[0] = getStoredNBTData();
		PacketPayload packet = new PacketPayload(xCoord, yCoord, zCoord, payload);
		return packet.makePacket();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setBoolean("open", open);
		nbt.setInteger("fuelLevel", fuelLevel);
		
		if (this.trayInventory != null) {
			NBTTagCompound trayContents = new NBTTagCompound();
			trayInventory.writeToNBT(trayContents);
			nbt.setTag("trayContents", trayContents);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.open = nbt.getBoolean("open");
		this.fuelLevel = nbt.getInteger("fuelLevel");
		
		if (nbt.hasKey("trayContents")) {
			NBTTagCompound trayContents = new NBTTagCompound();
			this.trayInventory = new InventoryItem(9, new ItemStack(EDItems.itemTray));
			this.trayInventory.readFromNBT(trayContents);
			this.hasTray = true;
		}
	}
	
	@Override
	public void onBlockActivated(EntityPlayer player) {
		if (this.open) {
			if (player.getCurrentEquippedItem() != null) {
				if (player.getCurrentEquippedItem().getItem() == EDItems.itemTray) {
					if (this.trayInventory == null) {
						this.trayInventory = new InventoryItem(9, player.getCurrentEquippedItem().copy());
						--player.getCurrentEquippedItem().stackSize;
						
						sendUpdatePacket(Side.CLIENT);
						return;
					}
				} else if (ItemUtil.getFuelValue(player.getCurrentEquippedItem()) > 0) {
					this.fuelLevel += ItemUtil.getFuelValue(player.getCurrentEquippedItem());
					--player.getCurrentEquippedItem().stackSize;
					
					sendUpdatePacket(Side.CLIENT);
					return;
				}
			} else if (this.trayInventory != null && player.isSneaking()) {
				BlockUtil.dropItemFromBlock(worldObj, xCoord, yCoord, zCoord, this.trayInventory.parent.copy(), new Random());
				
				this.trayInventory = null;
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
