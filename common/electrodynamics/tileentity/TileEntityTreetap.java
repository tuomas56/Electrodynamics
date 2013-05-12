package electrodynamics.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.lib.block.BlockIDs;

public class TileEntityTreetap extends TileEntity {

	/** Side the treetap is attached to */
	public ForgeDirection rotation;
	
	/** Does treetap has bucket? */
	public boolean hasBucket;
	
	/** Liquid amount */
	public int liquidAmount;
	
	/** Should tile entity send out update packet */
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (dirty) {
			PacketDispatcher.sendPacketToAllInDimension(getDescriptionPacket(), this.worldObj.provider.dimensionId);
			dirty = false;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		tag.setByte("direction", (byte) rotation.ordinal());
		tag.setByte("hasBucket", (byte) (hasBucket == true ? 1 : 0));
		tag.setInteger("liquidAmount", liquidAmount);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		rotation = ForgeDirection.VALID_DIRECTIONS[tag.getByte("direction")];
		hasBucket = tag.getByte("hasBucket") == 1 ? true : false;
		liquidAmount = tag.getInteger("liquidAmount");
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.customParam1);
		dirty = true;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, comp);
	}
	
	public boolean isTapSupported() {
		int x = xCoord + rotation.getOpposite().offsetX;
		int y = yCoord;
		int z = zCoord + rotation.getOpposite().offsetZ;
		
		return (this.worldObj.getBlockId(x, y, z) == BlockIDs.BLOCK_RUBBER_WOOD_ID);
	}
	
	public boolean isBucketSupported() {
		return (this.worldObj.isBlockSolidOnSide(xCoord, yCoord - 1, zCoord, ForgeDirection.UP));
	}
	
	public ItemStack getBucket() {
		return new ItemStack(Item.bucketEmpty);
	}
	
	public int getAttachedTreeHeight() {
		if (rotation != null) {
			boolean foundEnd = false;
			int height = 0;
			
			int x = xCoord + rotation.getOpposite().offsetX;
			int y = yCoord;
			int z = zCoord + rotation.getOpposite().offsetZ;
			
			while (!foundEnd) {
				if (this.worldObj.getBlockId(x, y, z) != BlockIDs.BLOCK_RUBBER_WOOD_ID) {
					foundEnd = true;
				} else {
					height += 1;
					y += 1;
				}
			}
			
			return height;
		}
		
		return 0;
	}
	
}
