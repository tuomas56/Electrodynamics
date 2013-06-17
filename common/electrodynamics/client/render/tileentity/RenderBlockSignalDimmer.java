package electrodynamics.client.render.tileentity;

import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelSignalDimmer;
import electrodynamics.tileentity.Connection;
import electrodynamics.tileentity.TileEntitySignalDimmer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class RenderBlockSignalDimmer extends TileEntitySpecialRenderer{
	private final ModelSignalDimmer model;
	
	public RenderBlockSignalDimmer(){
		this.model = new ModelSignalDimmer();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale){
		TileEntitySignalDimmer dimmer = (TileEntitySignalDimmer) tile;
		
		bindTextureByName("/mods/electrodynamics/textures/misc/signalDimmer.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderAllButWires(0.0625F);
		// attuneForConnections(dimmer);
		GL11.glPopMatrix();
	}
	
	@Deprecated()
	private void attuneForConnections(TileEntitySignalDimmer dimmer){
		if(isConnected(dimmer, ForgeDirection.NORTH)){
			model.renderNorthWire(0.0625F);
		}
		
		if(isConnected(dimmer, ForgeDirection.SOUTH)){
			model.renderSouthWire(0.0625F);
		}
	}
	
	private boolean isConnected(TileEntitySignalDimmer dimmer, ForgeDirection direction){
		return dimmer.allConnections().get(direction) != null;
	}
}