package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.tileentity.structure.TileEntityStructure.TileStructurePlaceHolder;

public class PacketClientData extends PacketED {

	public int x;
	public int y;
	public int z;
	
	public NBTTagCompound nbt;
	
	public PacketClientData() {
		super(PacketTypeHandler.CLIENT_DATA, false);
	}

	public PacketClientData(int x, int y, int z, NBTTagCompound nbt) {
		super(PacketTypeHandler.CLIENT_DATA, false);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.nbt = nbt;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.nbt = CompressedStreamTools.read(data);
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(this.x);
		dos.writeInt(this.y);
		dos.writeInt(this.z);
		CompressedStreamTools.write(this.nbt, dos);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		if (side == Side.CLIENT) {
			TileEntity tile = ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
			
			if (tile != null && tile instanceof TileStructurePlaceHolder) {
				((TileStructurePlaceHolder)tile).readClientData(this.nbt);
			}
		}
	}
	
}
