package electrodynamics.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.tileentity.TileEntityTable;

public class PacketTableUpdate extends PacketBlockCoord {

	public ItemStack tableContents;
	
	public PacketTableUpdate() {
		super(0, 0, 0);
	}
	
	public PacketTableUpdate(int x, int y, int z, ItemStack tableContents) {
		super(x, y, z);
		
		this.tableContents = tableContents;
	}
	
	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		TileEntity table = ((EntityPlayer)player).worldObj.getBlockTileEntity(this.x, this.y, this.z);
		
		if (table != null && table instanceof TileEntityTable) {
			((TileEntityTable)table).displayedItem = tableContents;
		}
	}

}
