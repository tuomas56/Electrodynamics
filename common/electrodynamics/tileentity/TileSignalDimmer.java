package electrodynamics.tileentity;

import java.util.HashMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileSignalDimmer extends TileEntity implements Connection{
	private boolean powered;
	private final HashMap<ForgeDirection, Connection> connections;
	
	public TileSignalDimmer(){
		this(false);
	}
	
	public TileSignalDimmer(boolean powered){
		this.powered = powered;
		this.connections = new HashMap<ForgeDirection, Connection>();
	}
	
	@Override
	public HashMap<ForgeDirection, Connection> allConnections(){
		return this.connections;
	}

	@Override
	public void setPowered(boolean powered){
		this.powered = powered;
	}

	@Override
	public boolean isPowered(){
		return this.powered;
	}
}