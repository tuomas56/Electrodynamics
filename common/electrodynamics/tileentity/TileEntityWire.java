package electrodynamics.tileentity;

import java.util.HashMap;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityWire extends TileEntity implements Connection{
	private final HashMap<ForgeDirection, Connection> connectionMap;
	
	public TileEntityWire(){
		this.connectionMap = new HashMap<ForgeDirection, Connection>();
	}
	
	@Override
	public HashMap<ForgeDirection, Connection> allConnections(){
		return this.connectionMap;
	}
	
	private void checkEast(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x - 1, y, z);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.EAST, (Connection) tile);
		} else{
			this.connectionMap.put(ForgeDirection.EAST, null);
		}
	}
	
	private void checkWest(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x + 1, y, z);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.WEST, (Connection) tile);
		} else{
			this.connectionMap.put(ForgeDirection.WEST, null);
		}
	}
	
	private void checkNorth(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z - 1);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.NORTH, (Connection) tile);
		} else{
			this.connectionMap.put(ForgeDirection.NORTH, null);
		}
	}
	
	private void checkSouth(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z + 1);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.SOUTH, (Connection) tile);
		} else{
			this.connectionMap.put(ForgeDirection.SOUTH, null);
		}
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		World world = this.worldObj;
		int x = this.xCoord;
		int y = this.yCoord;
		int z = this.zCoord;
		
		checkWest(world, x, y, z);
		checkEast(world, x, y, z);
		checkSouth(world, x, y, z);
		checkNorth(world, x, y, z);
	}
}