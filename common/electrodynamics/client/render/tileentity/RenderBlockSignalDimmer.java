package electrodynamics.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

import electrodynamics.client.model.ModelSignalDimmer;
import electrodynamics.lib.client.Textures;
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
		
		FMLClientHandler.instance().getClient().func_110434_K().func_110577_a(Textures.SIGNAL_DIMMER.resource);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
		model.renderAll(0.0625F);
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