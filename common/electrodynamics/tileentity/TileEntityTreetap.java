package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.block.BlockRubberWood;
import electrodynamics.item.EDItems;
import electrodynamics.lib.block.BlockIDs;

public class TileEntityTreetap extends TileEntity {

	/** Side the treetap is attached to */
	public ForgeDirection rotation;
	
	/** Does treetap has bucket? */
	public boolean hasBucket;
	
	/** Liquid amount */
	public int liquidAmount = 0;
	
	/** Should tile entity send out update packet */
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) return;
		
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
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, comp);
	}
	
	public void tick() {
		if (liquidAmount + 500 <= 1000 && hasBucket) {
			drawLatex();
		}
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
		return liquidAmount == 0 ? new ItemStack(Item.bucketEmpty) : new ItemStack(EDItems.itemLatexBucket);
	}
	
	public void dropBucket(EntityPlayer player) {
		player.inventory.addItemStackToInventory(getBucket());
		hasBucket = false;
		liquidAmount = 0;
		dirty = true;
	}
	
	public void drawLatex() {
		if (getHighestValidLog() > 0) {
			if (BlockRubberWood.suckLatex(this.worldObj, xCoord, yCoord + (getHighestValidLog() - 1), zCoord)) {
				this.liquidAmount += 500;
				this.dirty = true;
			}
		}
	}
	
	public int getHighestValidLog() {
		if (rotation != null) {
			boolean foundEnd = false;
			int height = 0;
			
			int x = xCoord + rotation.getOpposite().offsetX;
			int y = yCoord;
			int z = zCoord + rotation.getOpposite().offsetZ;
			
			while (!foundEnd) {
				if (this.worldObj.getBlockId(x, y, z) == BlockIDs.BLOCK_RUBBER_WOOD_ID) {
					if ((this.worldObj.getBlockMetadata(x, y, z) != 0 && this.worldObj.getBlockMetadata(x, y, z) <= 9)) {
						foundEnd = true;
						break;
					}
				} else {
					foundEnd = true;
					break;
				}
				
				height += 1;
				y += 1;
			}
			
			return height;
		}
		
		return 0;
	}
	
}
