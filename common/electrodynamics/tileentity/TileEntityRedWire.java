package electrodynamics.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.network.IPayloadReceiver;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketPayload;

public class TileEntityRedWire extends TileEntity implements IPayloadReceiver {

	/** If this block is being powered from somewhere */
	public boolean isPowered;
	
	/** Sides that should prevent redstone interaction */
	public boolean[] activeMasks;
	
	public TileEntityRedWire() {
		activeMasks = new boolean[ForgeDirection.VALID_DIRECTIONS.length];
		isPowered = false;
	}
	
	public void updateEntity() {
		System.out.println(FMLCommonHandler.instance().getEffectiveSide() + ": " + isPowered);
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		NBTTagList nbtActiveMasks = new NBTTagList();
		for (int i=0; i<activeMasks.length; i++) {
			nbtActiveMasks.appendTag(new NBTTagByte("", (byte) (activeMasks[i] == true ? 1 : 0)));
		}
		nbt.setTag("activeMasks", nbtActiveMasks);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		if (nbt.hasKey("activeMasks")) {
			activeMasks = new boolean[6];
			
			NBTTagList nbtActiveMasks = nbt.getTagList("activeMasks");
			
			for (int i=0; i<nbtActiveMasks.tagCount(); i++) {
				NBTTagByte bool = (NBTTagByte) nbtActiveMasks.tagAt(i);
				activeMasks[i] = bool.data == 1 ? true : false;
			}
		}
	}
	
	@Override
	public void handlePayload(byte[] data) {
		for (int i=0; i<6; i++) {
			activeMasks[i] = data[i] == 1 ? true : false;
		}
		isPowered = data[6] == 1 ? true : false;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		byte[] data = new byte[activeMasks.length + 1];
		for (int i=0; i<activeMasks.length; i++) {
			data[i] = (byte) (activeMasks[i] == true ? 1 : 0);
		}
		data[6] = (byte) (isPowered == true ? 1 : 0);
		
		PacketPayload packet = new PacketPayload(xCoord, yCoord, zCoord, data);
	
		return PacketTypeHandler.fillPacket(packet);
	}
	
	/** Should only be run when the block is first placed */
	public void updateMasks() {
		for (int i=0; i<ForgeDirection.VALID_DIRECTIONS.length; i++) {
			ForgeDirection fDir = ForgeDirection.getOrientation(i);
			
			if (isProvidingAnyPower(this.worldObj, this.xCoord + fDir.offsetX, this.yCoord + fDir.offsetY, this.zCoord + fDir.offsetZ, fDir)) {
				activeMasks[i] = true;
			}
		}
	}
	
	public void scanAndUpdate() {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (!activeMasks[dir.ordinal()]) {
				if (isProvidingAnyPower(this.worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir)) {
					System.out.println("Powered on " + dir.toString() + " side");
					isPowered = true;
					return;
				}
			}
		}
		
		isPowered = false;
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
	
}
