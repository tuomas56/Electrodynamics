package electrodynamics.tileentity;

import java.util.HashMap;

import net.minecraftforge.common.ForgeDirection;

public interface Connection{
	public HashMap<ForgeDirection, Connection> allConnections();
	public void setPowered(boolean powered);
	public boolean isPowered();
}
