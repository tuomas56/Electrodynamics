package electrodynamics.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.tileentity.TileEntityTable;

public class PacketClearTable extends PacketBlockCoord {

	public PacketClearTable() {
		super(0, 0, 0);
	}
	
	public PacketClearTable(int x, int y, int z) {
		super(x, y, z);
	}
	
	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		((TileEntityTable)((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z)).displayedItem = null;
	}

}
