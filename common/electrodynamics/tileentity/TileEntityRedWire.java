package electrodynamics.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.block.EDBlocks;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.Payload;
import electrodynamics.network.packet.PacketPayload;

public class TileEntityRedWire extends TileEntity implements IPayloadReceiver {

	/** If this block is being powered from somewhere */
	public boolean isPowered;

	/** The previous powered state of the wire. Can be the same as isPowered */
	public boolean prevIsPowered;

	/** Sides that should prevent redstone interaction */
	public boolean[] activeMasks;

	/** Sides that have a valid connection with another wire/block */
	@SideOnly(Side.CLIENT)
	public boolean[] validConnections;

	/** Wire validConnections dirty state */
	@SideOnly(Side.CLIENT)
	public boolean connectionsDirty;

	public TileEntityRedWire() {
		activeMasks = new boolean[ForgeDirection.VALID_DIRECTIONS.length];
		validConnections = new boolean[ForgeDirection.VALID_DIRECTIONS.length];
		connectionsDirty = true;
		isPowered = false;
		prevIsPowered = false;
	}

	@Override
	public void updateEntity() {
		if (connectionsDirty) {
			scanAndUpdate();
			updateConnections();
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		NBTTagList nbtActiveMasks = new NBTTagList();
		for (int i = 0; i < activeMasks.length; i++) {
			nbtActiveMasks.appendTag(new NBTTagByte("", (byte) (activeMasks[i] == true ? 1 : 0)));
		}
		nbt.setTag("activeMasks", nbtActiveMasks);
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		if (nbt.hasKey("activeMasks")) {
			activeMasks = new boolean[6];

			NBTTagList nbtActiveMasks = nbt.getTagList("activeMasks");

			for (int i = 0; i < nbtActiveMasks.tagCount(); i++) {
				NBTTagByte bool = (NBTTagByte) nbtActiveMasks.tagAt(i);
				activeMasks[i] = bool.data == 1 ? true : false;
			}
		}
	}

	

	/** Should only be run when the block is first placed */
	public void updateMasks() {
		for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
			ForgeDirection fDir = ForgeDirection.getOrientation(i);

			if (isProvidingAnyPower(this.worldObj, this.xCoord + fDir.offsetX, this.yCoord + fDir.offsetY, this.zCoord + fDir.offsetZ, fDir)) {
				System.out.println("Block on " + fDir.toString() + " side is providing power.");
				activeMasks[i] = true;
			}
		}
	}

	public void scanAndUpdate() {
		prevIsPowered = isPowered;

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (!activeMasks[dir.ordinal()]) {
				int x = xCoord + dir.offsetX;
				int y = yCoord + dir.offsetY;
				int z = zCoord + dir.offsetZ;

				if (isProvidingAnyPower(this.worldObj, x, y, z, dir)) {
					isPowered = true;
					return;
				}
				
				if (isPowered != prevIsPowered) {
					this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, EDBlocks.blockRedWire.blockID);
				}
			}
		}

		isPowered = false;
		connectionsDirty = true;
	}

	@SideOnly(Side.CLIENT)
	public void updateConnections() {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (!activeMasks[dir.ordinal()]) {
				int x = xCoord + dir.offsetX;
				int y = yCoord + dir.offsetY;
				int z = zCoord + dir.offsetZ;

				if (shouldConnect(this.worldObj, x, y, z, dir)) {
					validConnections[dir.ordinal()] = true;
				} else {
					validConnections[dir.ordinal()] = false;
				}
			}
		}

		connectionsDirty = false;
	}

	/**
	 * Should the wire calling this connect to the block at the specified
	 * coordinates
	 */
	public boolean shouldConnect(World world, int x, int y, int z, ForgeDirection side) {
		return (isProvidingAnyPower(world, x, y, z, side.getOpposite()) || world.getBlockId(x, y, z) == EDBlocks.blockRedWire.blockID);
	}

	public boolean isConnected(ForgeDirection side) {
		if (!activeMasks[side.ordinal()]) {
			return validConnections[side.ordinal()];
		}
		
		return false;
	}

	private boolean isProvidingAnyPower(World world, int x, int y, int z, ForgeDirection side) {
		int blockID = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];

		if (block != null) {
			if (block.isProvidingStrongPower(world, x, y, z, side.getOpposite().ordinal()) > 0 || block.isProvidingWeakPower(world, x, y, z, side.getOpposite().ordinal()) > 0 || world.getIndirectPowerOutput(x, y, z, side.ordinal())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void handlePayload(Payload payload) {
		// TODO Auto-generated method stub
		
	}

}
