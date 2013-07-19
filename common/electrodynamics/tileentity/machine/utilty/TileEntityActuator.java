package electrodynamics.tileentity.machine.utilty;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.api.tool.ITool;
import electrodynamics.api.tool.ToolType;
import electrodynamics.core.CoreUtils;
import electrodynamics.inventory.InventoryLimited;
import electrodynamics.tileentity.TileEntityInventoryWrapper;
import electrodynamics.util.ItemUtil;

public class TileEntityActuator extends TileEntityInventoryWrapper implements ISidedInventory {

	@SideOnly(Side.CLIENT)
	public int blockRotation = 0;

	public byte mode = 0;
	
	public NBTTagCompound tileEntityData = null;

	private ItemStack previousItem = null;
	
	private boolean prevRedstoneState = false;
	
	public TileEntityActuator() {
		super(new InventoryLimited("Actuator", 1, 1));
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		ForgeDirection sideDir = ForgeDirection.getOrientation(side);
		return sideDir == this.rotation.getOpposite() ? new int[] { 0 } : null;
	}

	@Override
	public void updateEntityClient() {
		if (this.blockRotation < 360) {
			this.blockRotation += 2;
		} else {
			this.blockRotation = 0;
		}
	}

	@Override
	public void updateEntityServer() {
		if (!ItemUtil.areItemStacksEqual(getStackInSlot(0), previousItem, true)) {
			sendBlockUpdate();
		}
	}
	
	@Override
	public void onNeighborUpdate() {
		boolean redstoneState = this.worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		
		if (!prevRedstoneState && redstoneState) {
			update();
			prevRedstoneState = true;
		} else if (prevRedstoneState && !redstoneState) {
			this.prevRedstoneState = false;
		}
	}
	
	private void update() {
		ItemStack stack = this.getStackInSlot(0);
		int[] offsetCoords = this.getOffsetCoords();
		
		if (stack != null && getBlockInFront() == 0 && (this.mode == Mode.DEFAULT.ordinal() || this.mode == Mode.PLACE.ordinal())) { // Place block
			this.worldObj.setBlock(offsetCoords[0], offsetCoords[1], offsetCoords[2], stack.itemID, stack.getItemDamage(), 3);

			TileEntity tile = this.worldObj.getBlockTileEntity(offsetCoords[0], offsetCoords[1], offsetCoords[2]);

			if (tile != null) {
				tile.readFromNBT(this.tileEntityData);
				this.tileEntityData = null;
			}
			
			this.setInventorySlotContents(0, null);
			sendBlockUpdate();
		} else if (stack == null && getBlockInFront() > 0 && (this.mode == Mode.DEFAULT.ordinal() || this.mode == Mode.BREAK.ordinal())) { // Pickup block
			int id = this.worldObj.getBlockId(offsetCoords[0], offsetCoords[1], offsetCoords[2]);
			int meta = this.worldObj.getBlockMetadata(offsetCoords[0], offsetCoords[1], offsetCoords[2]);
			ItemStack picked = new ItemStack(id, 1, meta);

			TileEntity tile = this.worldObj.getBlockTileEntity(offsetCoords[0], offsetCoords[1], offsetCoords[2]);

			if (tile != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				tile.writeToNBT(nbt);
				this.tileEntityData = nbt;
				tile.invalidate();
			}
			
			this.worldObj.setBlockToAir(offsetCoords[0], offsetCoords[1], offsetCoords[2]);
			this.setInventorySlotContents(0, picked);
			sendBlockUpdate();
		}
	}
	
	@Override
	public void onBlockActivated(EntityPlayer player) {
		if (!CoreUtils.isServer(this.worldObj)) return;
		
		if (player.getCurrentEquippedItem() != null) {
			ItemStack current = player.getCurrentEquippedItem();
			
			if (current.getItem() instanceof ITool && ((ITool)current.getItem()).getToolType() == ToolType.HAMMER) {
				player.sendChatToPlayer("Actuator mode changed: " + EnumChatFormatting.RED + switchMode().toString());
			}
		}
	}
	
	private Mode switchMode() {
		if (this.mode + 1 < Mode.values().length) {
			this.mode++;
		} else {
			this.mode = 0;
		}
		
		return Mode.values()[this.mode];
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setByte("mode", this.mode);
		
		ItemStack stack = this.getStackInSlot(0);
		if (stack != null) {
			NBTTagCompound item = new NBTTagCompound();
			stack.writeToNBT(item);
			nbt.setTag("block", item);
		} else {
			nbt.setString("block", "null");
		}

		if (this.tileEntityData != null) {
			nbt.setCompoundTag("data", this.tileEntityData);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		this.mode = nbt.getByte("mode");
		
		if (nbt.hasKey("block")) {
			NBTBase block = nbt.getTag("block");
			
			if (block instanceof NBTTagCompound) {
				this.setInventorySlotContents(0, ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("block")));
			} else if (block instanceof NBTTagString) {
				this.setInventorySlotContents(0, null);
			}
		}

		if (nbt.hasKey("data")) {
			this.tileEntityData = nbt.getCompoundTag("data");
		}
	}

	@Override
	public void getDescriptionForClient(NBTTagCompound nbt) {
		super.getDescriptionForClient(nbt);
		writeToNBT(nbt);
	}

	@Override
	public void onDescriptionPacket(NBTTagCompound nbt) { // load
		super.onDescriptionPacket(nbt);
		readFromNBT(nbt);
	}

	@Override
	public void onUpdatePacket(NBTTagCompound nbt) { // update
		super.onUpdatePacket(nbt);
		readFromNBT(nbt);
	}

	private void sendBlockUpdate() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		sendUpdatePacket(nbt);
	}

	public enum Mode {
		DEFAULT, BREAK, PLACE;
	}
	
}
