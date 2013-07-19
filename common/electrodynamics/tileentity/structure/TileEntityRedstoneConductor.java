package electrodynamics.tileentity.structure;

import electrodynamics.interfaces.IRedstoneUser;
import net.minecraft.entity.player.EntityPlayer;

public class TileEntityRedstoneConductor extends TileEntityStructure {

	@Override
	public void onBlockUpdate() {
		if (this.isValidStructure() && !this.isCentralTileEntity()) {
			TileEntityStructure central = this.getCentralTileEntity();
			
			if (central != null && central instanceof IRedstoneUser) {
				((IRedstoneUser)central).updateSignalStrength(this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord) ? 15 : 0);
			}
		}
	}
	
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

}
