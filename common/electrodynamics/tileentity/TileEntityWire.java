package electrodynamics.tileentity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityWire extends TileEntity implements Connection{
	private final HashMap<ForgeDirection, Connection> connectionMap;
	private boolean isPowered = false;
	private final LinkedList<Integer> dummyConnections = new LinkedList<Integer>();
	
	{
		dummyConnections.add(Block.lever.blockID);
		dummyConnections.add(Block.redstoneLampIdle.blockID);
		dummyConnections.add(Block.redstoneLampActive.blockID);
		dummyConnections.add(Block.redstoneComparatorActive.blockID);
		dummyConnections.add(Block.redstoneComparatorIdle.blockID);
		dummyConnections.add(Block.pistonBase.blockID);
		dummyConnections.add(Block.pistonStickyBase.blockID);
		dummyConnections.add(Block.redstoneWire.blockID);
		dummyConnections.add(Block.redstoneRepeaterActive.blockID);
		dummyConnections.add(Block.redstoneRepeaterIdle.blockID);
		dummyConnections.add(Block.chestTrapped.blockID);
		dummyConnections.add(Block.daylightSensor.blockID);
		dummyConnections.add(Block.commandBlock.blockID);
	}
	
	public TileEntityWire(){
		this.connectionMap = new HashMap<ForgeDirection, Connection>();
	}
	
	@Override
	public HashMap<ForgeDirection, Connection> allConnections(){
		return this.connectionMap;
	}
	
	public boolean isPowered(){
		return this.isPowered;
	}
	
	public void setPowered(boolean powered){
		this.isPowered = powered;
	}
	
	private void checkEast(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x - 1, y, z);
		int blockId = world.getBlockId(x - 1, y, z);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.EAST, (Connection) tile);
		} else if(isDummyConnection(blockId)){
			this.connectionMap.put(ForgeDirection.EAST, new DummyConnection());
		} else{
			this.connectionMap.put(ForgeDirection.EAST, null);
		}
	}
	
	private void checkWest(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x + 1, y, z);
		int blockId = world.getBlockId(x + 1, y, z);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.WEST, (Connection) tile);
		} else if(isDummyConnection(blockId)){
			this.connectionMap.put(ForgeDirection.WEST, new DummyConnection());
		} else{
			this.connectionMap.put(ForgeDirection.WEST, null);
		}
	}
	
	private void checkNorth(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z - 1);
		int blockId = world.getBlockId(x, y, z - 1);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.NORTH, (Connection) tile);
		} else if(isDummyConnection(blockId)){
			this.connectionMap.put(ForgeDirection.NORTH, new DummyConnection());
		} else{
			this.connectionMap.put(ForgeDirection.NORTH, null);
		}
	}
	
	private void checkSouth(World world, int x, int y, int z){
		TileEntity tile = world.getBlockTileEntity(x, y, z + 1);
		int blockId = world.getBlockId(x, y, z + 1);
		
		if(tile instanceof Connection){
			this.connectionMap.put(ForgeDirection.SOUTH, (Connection) tile);
		} else if(isDummyConnection(blockId)){
			this.connectionMap.put(ForgeDirection.SOUTH, new DummyConnection());
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
		
		if(world.isBlockIndirectlyGettingPowered(x, y, z)){
			this.isPowered = true;
		} else{
			this.isPowered = false;
		}
		
		checkWest(world, x, y, z);
		checkEast(world, x, y, z);
		checkSouth(world, x, y, z);
		checkNorth(world, x, y, z);
	}
	
	@Deprecated
	private void updateConnectionsPowerStatus(){
		for(Entry<ForgeDirection, Connection> connection : this.allConnections().entrySet()){
			
		}
	}
	
	private boolean isDummyConnection(int id){
		return dummyConnections.contains(id);
	}
	
	private final class DummyConnection implements Connection{
		@Override public HashMap<ForgeDirection, Connection> allConnections(){ return null; }
		@Override public void setPowered(boolean powered){}
		@Override public boolean isPowered(){ return false;}
	}
}