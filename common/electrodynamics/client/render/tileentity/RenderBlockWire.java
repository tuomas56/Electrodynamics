package electrodynamics.client.render.tileentity;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.model.ModelWire;
import electrodynamics.tileentity.Connection;
import electrodynamics.tileentity.TileEntityRedWire;

import static net.minecraftforge.common.ForgeDirection.*;

public class RenderBlockWire extends TileEntitySpecialRenderer{
	private final float scale = 0.0625F;
	private final ModelWire model;
	private final HashMap<Integer, ForgeDirection> oridnal = new HashMap<Integer, ForgeDirection>();
	
	{
		oridnal.put(0, UP);
		oridnal.put(1, DOWN);
		oridnal.put(2, NORTH);
		oridnal.put(3, SOUTH);
		oridnal.put(4, WEST);
		oridnal.put(5, EAST);
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
		renderOrientation(((TileEntityRedWire) tile).getOrientation());
		GL11.glPopMatrix();
	}
	
	private void renderOrientation(int side){
		ForgeDirection dir = oridnal.get(side);

		switch(dir)
		{
		case UP:
			model.renderTopWire(0.0625F, 5);
			break;
		case DOWN:
			model.renderBottomWire(0.0625F, 5);
			break;
		case WEST:
			model.renderLeftWire(0.0625F, 5);
			break;
		case EAST:
			model.renderRightWire(0.0625F, 5);
			break;
		case NORTH:
			model.renderFrontWire(0.0625F, 5);
			break;
		case SOUTH:
			model.renderBackWire(0.0625F, 5);
			break;
		default:
			break;
		}
	}
	
	private boolean isConnected(Entry<ForgeDirection, Connection> entry){
		return entry.getValue() != null;
	}
}