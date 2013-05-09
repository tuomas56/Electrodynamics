package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.tileentity.TileEntityTable;

public class PacketTableUpdate extends PacketED {

	public int x;
	public int y;
	public int z;
	
	public ItemStack tableContents;
	
	public PacketTableUpdate() {
		super(PacketTypeHandler.TABLE_UPDATE, true);
	}
	
	public PacketTableUpdate(int x, int y, int z, ItemStack tableContents) {
		super(PacketTypeHandler.TABLE_UPDATE, true);
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.tableContents = tableContents;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.tableContents = Packet.readItemStack(data);
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
		Packet.writeItemStack(this.tableContents, dos);
	}
	
	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		TileEntity table = ((EntityPlayer)player).worldObj.getBlockTileEntity(this.x, this.y, this.z);
		
		if (table != null && table instanceof TileEntityTable) {
			((TileEntityTable)table).setItem(tableContents);
		}
	}

}
