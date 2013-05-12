package electrodynamics.tileentity;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityMachine extends TileEntity {

	public ForgeDirection rotation;

	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (dirty) {
			PacketDispatcher.sendPacketToAllInDimension(this.getDescriptionPacket(), this.worldObj.provider.dimensionId);
			dirty = false;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		tag.setByte("direction", (byte) rotation.ordinal());
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		rotation = ForgeDirection.VALID_DIRECTIONS[tag.getByte("direction")];
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound comp = new NBTTagCompound();
		this.writeToNBT(comp);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, comp);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		return false;
	}
	
}
