package electrodynamics.world;


import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import electrodynamics.mbs.MBSManager;
import electrodynamics.mbs.util.MBSUtil;
import electrodynamics.mbs.util.WorldChunk;
import electrodynamics.mbs.util.WorldCoordinate;
import net.minecraft.world.IBlockAccess;

import java.util.EnumSet;
import java.util.LinkedList;

public class TickHandlerMBS implements ITickHandler {

	private static TickHandlerMBS instance;

	public static TickHandlerMBS instance() {
		if( instance == null )
			instance = new TickHandlerMBS();
		return instance;
	}

	private LinkedList<Task> queue = new LinkedList<Task>();
	private boolean queueEmpty = true;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if( queueEmpty )
			return;

		Task task = queue.poll();
		queueEmpty = queue.isEmpty();

		WorldChunk chunk = MBSUtil.getChunkSurrounding( task );
		if( chunk != null ) {
			if( task.doValidate ) {
				MBSManager.updateStructuresAt( chunk );
			} else {
				MBSManager.invalidateChunk( chunk );
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of( TickType.WORLD );
	}

	@Override
	public String getLabel() {
		return "ED-MBS";
	}

	public void scheduleTask(IBlockAccess world, int x, int y, int z, boolean doValidate) {
		queue.offer( new Task( world, x, y, z, doValidate ) );
		queueEmpty = false;
	}

	private class Task extends WorldCoordinate {

		final boolean doValidate;

		public Task(IBlockAccess access, int x, int y, int z, boolean doValidate) {
			super( access, x, y, z );
			this.doValidate = doValidate;
		}
	}

}
