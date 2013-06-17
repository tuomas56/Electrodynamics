package electrodynamics.client.render.tileentity;

import java.util.Map.Entry;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelWire;
import electrodynamics.tileentity.Connection;
import electrodynamics.tileentity.TileConnection;

public class RenderBlockWire extends TileEntitySpecialRenderer{
	private final ModelWire model;
	
	public RenderBlockWire(){
		this.model = new ModelWire();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale){
		bindTextureByName("/mods/electrodynamics/textures/misc/redAlloyWire.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		model.renderCenter(0.0265F);
		renderSides(tile);
		GL11.glPopMatrix();
	}
	
	private void renderSides(TileEntity tile){
		TileConnection wire = (TileConnection) tile;
		
		for(Entry<ForgeDirection, Connection> entry : wire.allConnections().entrySet()){
			if(isConnected(entry)){
				renderSide(entry.getKey());
			} else{
				continue;
			}
		}
	}
	
	private boolean isConnected(Entry<ForgeDirection, Connection> entry){
		return entry.getValue() != null;
	}
	
	private void renderSide(ForgeDirection direction){
		switch(direction)
		{
		case EAST:
			model.renderEast(0.0625F);
			break;
		case WEST:
			model.renderWest(0.0625F);
			break;
		case NORTH:
			model.renderNorth(0.0625F);
			break;
		case SOUTH:
			model.renderSouth(0.0625F);
		default:
			break;
		}
	}
}
