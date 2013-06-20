package electrodynamics.tileentity;

import java.util.HashMap;
import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TileConnection extends TileEntity implements Connection{
	private final HashMap<ForgeDirection, Connection> connections;
	private boolean isPowered;
	private final LinkedList<Integer> dummyConnectionsList;
	private int orientation;
	
	/**
	 * Index #1: East
	 * Index #2: West
	 * Index #3: North
	 * Index #4: South
	 * Index #5: Up
	 * Index $6: Down
	 */
	protected final int[][] ordinalMatrix = new int[][]{
			new int[]{-1, 0, 0},
			new int[]{1, 0, 0},
			new int[]{0, 0, -1},
			new int[]{0, 0, 1},
			new int[]{0, 1, 0},
			new int[]{0, -1, 0}
	};
	
	protected TileConnection(){
		this(false);
	}
	
	public int getOrientation(){
		return this.orientation;
	}
	
	public void setOrientation(int orientation){
		this.orientation = orientation;
	}
	
	protected TileConnection(boolean isPowered){
		this.isPowered = isPowered;
		this.connections = new HashMap<ForgeDirection, Connection>();
	}

	{
		this.dummyConnectionsList = new LinkedList<Integer>();

		dummyConnectionsList.add(Block.blockRedstone.blockID);
		dummyConnectionsList.add(Block.lever.blockID);
		dummyConnectionsList.add(Block.redstoneLampIdle.blockID);
		dummyConnectionsList.add(Block.redstoneLampActive.blockID);
		dummyConnectionsList.add(Block.redstoneComparatorActive.blockID);
		dummyConnectionsList.add(Block.redstoneComparatorIdle.blockID);
		dummyConnectionsList.add(Block.pistonBase.blockID);
		dummyConnectionsList.add(Block.pistonStickyBase.blockID);
		dummyConnectionsList.add(Block.redstoneWire.blockID);
		dummyConnectionsList.add(Block.redstoneRepeaterActive.blockID);
		dummyConnectionsList.add(Block.redstoneRepeaterIdle.blockID);
		dummyConnectionsList.add(Block.chestTrapped.blockID);
		dummyConnectionsList.add(Block.daylightSensor.blockID);
		dummyConnectionsList.add(Block.commandBlock.blockID);
	}
	
	@Override
	public HashMap<ForgeDirection, Connection> allConnections(){
		return this.connections;
	}

	@Override
	public void setPowered(boolean powered){
		this.isPowered = powered;
	}

	@Override
	public boolean isPowered(){
		return this.isPowered;
	}
	
	protected void addDummyConnection(int blockId){
		this.dummyConnectionsList.add(blockId);
	}
	
	protected boolean isDummyConnection(int blockId){
		return this.dummyConnectionsList.contains(blockId);
	}
	
	protected void checkEast(World world, int x, int y, int z){
		int blockId = getBlockIdFromWorld(0, world, x, y, z);
		TileEntity entity = getTileFromWorld(0, world, x, y, z);
		
		if(entity instanceof Connection){
			addConnection(ForgeDirection.EAST, entity);
		} else if(this.isDummyConnection(blockId)){
			addConnection(ForgeDirection.EAST, new DummyConnection());
		} else{
			addConnection(ForgeDirection.EAST, null);
		}
	}
	
	protected void checkWest(World world, int x, int y, int z){
		int blockId = getBlockIdFromWorld(1, world, x, y, z);
		TileEntity tile = getTileFromWorld(1, world, x, y, z);
		
		if(tile instanceof Connection){
			addConnection(ForgeDirection.WEST, tile);
		} else if(this.isDummyConnection(blockId)){
			addConnection(ForgeDirection.WEST, new DummyConnection());
		} else{
			addConnection(ForgeDirection.WEST, null);
		}
	}
	
	protected void checkNorth(World world, int x, int y, int z){
		int blockId = getBlockIdFromWorld(2, world, x, y, z);
		TileEntity tile = getTileFromWorld(2, world, x, y, z);
		
		if(tile instanceof Connection){
			addConnection(ForgeDirection.NORTH, tile);
		} else if(this.isDummyConnection(blockId)){
			addConnection(ForgeDirection.NORTH, new DummyConnection());
		} else{
			addConnection(ForgeDirection.NORTH, null);
		}
	}
	
	protected void checkSouth(World world, int x, int y, int z){
		int blockId = getBlockIdFromWorld(3, world, x, y, z);
		TileEntity tile = getTileFromWorld(3, world, x, y, z);
		
		if(tile instanceof Connection){
			addConnection(ForgeDirection.SOUTH, tile);
		} else if(this.isDummyConnection(blockId)){
			addConnection(ForgeDirection.SOUTH, new DummyConnection());
		} else{
			addConnection(ForgeDirection.SOUTH, null);
		}
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		
		checkAllOrdinals(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}
	
	protected void checkAllOrdinals(World world, int x, int y, int z){
		checkWest(world, x, y, z);
		checkEast(world, x, y, z);
		checkSouth(world, x, y, z);
		checkNorth(world, x, y, z);
	}
	
	protected void addConnection(ForgeDirection direction, Object obj){
		this.connections.put(direction, (Connection) obj);
	}
	
	protected int getBlockIdFromWorld(int matrixIndex, World world, int x, int y, int z){
		int[] matrix = this.ordinalMatrix[matrixIndex];
		
		return world.getBlockId(x + matrix[0], y + matrix [1], z + matrix[2]);
	}
	
	protected TileEntity getTileFromWorld(int matrixIndex, World world, int x, int y, int z){
		int[] matrix = this.ordinalMatrix[matrixIndex];
		
		return world.getBlockTileEntity(x + matrix[0], y + matrix[1], z + matrix[2]);
	}
	
	private final class DummyConnection implements Connection{
		@Override public HashMap<ForgeDirection, Connection> allConnections(){ return null; }
		@Override public void setPowered(boolean powered){}
		@Override public boolean isPowered(){ return false; }
	}
}