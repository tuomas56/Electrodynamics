package electrodynamics.core;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import electrodynamics.client.fx.FXBeam;
import electrodynamics.lib.Strings;
import electrodynamics.tileentity.TileEntityRedstoneWire;
import electrodynamics.tileentity.TileEntityTable;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRedstoneWire.class, Strings.BLOCK_RED_WIRE_NAME);
		GameRegistry.registerTileEntity(TileEntityTable.class, Strings.BLOCK_TABLE_NAME);
	}
	
	public void registerEntities() {
		EntityRegistry.registerGlobalEntityID(FXBeam.class, "entityLaser", 150);
	}
	
	public void registerRenders() {
		
	}
	
	public void setKeyBinding(String name, int value, boolean repeats) {
		
	}
	
	public void registerKeyBindings() {
		
	}
	
}
