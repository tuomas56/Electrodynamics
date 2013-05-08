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

	private LinkedList<WorldCoordinate> queue = new LinkedList<WorldCoordinate>();
	private boolean queueEmpty = true;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if( queueEmpty )
			return;

		WorldCoordinate coords = queue.poll();
		queueEmpty = queue.isEmpty();

		WorldChunk chunk = MBSUtil.getChunkSurrounding( coords );
		if( chunk != null ) {
			MBSManager.updateStructuresAt( chunk );
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

	public void scheduleTask(IBlockAccess world, int x, int y, int z) {
		queue.offer( new WorldCoordinate( world, x, y, z ) );
		queueEmpty = false;
	}


}
