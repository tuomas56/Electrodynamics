package electrodynamics.tileentity;

import java.util.HashMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileGate extends TileEntity implements Connection{
	private final HashMap<ForgeDirection, Connection> connections;
	private final HashMap<ForgeDirection, Boolean> allowedConnections;
	
	public TileGate(){
		this.connections = new HashMap<ForgeDirection, Connection>();
		this.allowedConnections = new HashMap<ForgeDirection, Boolean>();
	}

	@Override
	public HashMap<ForgeDirection, Connection> allConnections(){
		return this.connections;
	}

	@Override
	public void setPowered(boolean powered){
		return;
	}

	@Override
	public boolean isPowered(){
		return false;
	}	
}