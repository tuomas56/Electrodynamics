package electrodynamics.client.render.tileentity;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import static net.minecraftforge.common.ForgeDirection.*;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelWire;
import electrodynamics.tileentity.Connection;
import electrodynamics.tileentity.TileEntityRedWire;

public class RenderBlockWire extends TileEntitySpecialRenderer{
	private final float scale = 0.0625F;
	private final ModelWire model;
	
	private final HashMap<ForgeDirection, Integer> directionalCallMapFront = new HashMap<ForgeDirection, Integer>();
	private final HashMap<ForgeDirection, Integer> directionalCallMapBack = new HashMap<ForgeDirection, Integer>();
	private final HashMap<ForgeDirection, Integer> directionalCallMapTop = new HashMap<ForgeDirection, Integer>();
	private final HashMap<ForgeDirection, Integer> directionalCallMapBottom = new HashMap<ForgeDirection, Integer>();
	private final HashMap<ForgeDirection, Integer> directionalCallMapLeft = new HashMap<ForgeDirection, Integer>();
	private final HashMap<ForgeDirection, Integer> directionalCallMapRight = new HashMap<ForgeDirection, Integer>();
	
	{
		directionalCallMapFront.put(NORTH, 1);
		directionalCallMapFront.put(SOUTH, 2);
		directionalCallMapFront.put(WEST, 3);
		directionalCallMapFront.put(EAST, 4);
	}
	
	public RenderBlockWire(){
		this.model = new ModelWire();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale){
		bindTextureByName("/mods/electrodynamics/textures/misc/redAlloyWire.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		adjustWireConnectionsForRender(tile);
		model.renderBottomWire(0.0625F, 5);
		GL11.glPopMatrix();
	}
	
	private boolean isConnected(Entry<ForgeDirection, Connection> entry){
		return entry.getValue() != null;
	}
	
	private void renderForgeDirection(Entry<ForgeDirection, Connection> entry){
		switch(entry.getKey())
		{
		case NORTH:
			model.renderBottomWire(scale, directionalCallMapBottom.get(entry.getKey()));
			break;
		case SOUTH:
			model.renderBottomWire(scale, directionalCallMapBottom.get(entry.getKey()));
			break;
		case WEST:
			model.renderBottomWire(scale, directionalCallMapBottom.get(entry.getKey()));
			break;
		case EAST:
			model.renderBottomWire(scale, directionalCallMapBottom.get(entry.getKey()));
			break;
		default:
			break;
		}
	}
	
	private void adjustWireConnectionsForRender(TileEntity tile){
		TileEntityRedWire wire = (TileEntityRedWire) tile;
		
		for(Entry<ForgeDirection, Connection> connection : wire.allConnections().entrySet()){
			if(isConnected(connection)){
				renderForgeDirection(connection);
				continue;
			} else{
				continue;
			}
		}
	}
}