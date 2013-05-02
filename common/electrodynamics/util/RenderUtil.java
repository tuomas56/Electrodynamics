package electrodynamics.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderUtil {

	public static final double OFFSET_CONSTANT = 0.01;
	
	public static void translateToWorldCoords(Entity entity, float frame) {       
        double interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * frame;
        double interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * frame;
        double interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * frame;
        
        GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);
    }
	
	public static void drawCubeAt(int x, int y, int z) {
		for (ForgeDirection face : ForgeDirection.VALID_DIRECTIONS) {
			drawQuadOnFace(x, y, z, face);
		}
	}
	
	public static void drawQuadOnFace(int x, int y, int z, ForgeDirection face) {
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		
		switch(face) {
			case UP: {
				tess.addVertex(x, y + 1 + OFFSET_CONSTANT, z);
				tess.addVertex(x, y + 1 + OFFSET_CONSTANT, z + 1);
				tess.addVertex(x + 1, y + 1 + OFFSET_CONSTANT, z + 1);
				tess.addVertex(x + 1, y + 1 + OFFSET_CONSTANT, z);
				
			}
			
			case DOWN: {
				tess.addVertex(x + 1, y - OFFSET_CONSTANT, z);
				tess.addVertex(x + 1, y - OFFSET_CONSTANT, z + 1);
				tess.addVertex(x,     y - OFFSET_CONSTANT, z + 1);
				tess.addVertex(x,     y - OFFSET_CONSTANT, z);
			}
			
			case EAST: {
				tess.addVertex(x + 1 + OFFSET_CONSTANT, y,     z);
				tess.addVertex(x + 1 + OFFSET_CONSTANT, y + 1, z);
				tess.addVertex(x + 1 + OFFSET_CONSTANT, y + 1, z + 1);
				tess.addVertex(x + 1 + OFFSET_CONSTANT, y,     z + 1);
			}
			
			case NORTH: {
				tess.addVertex(x,     y,     z - OFFSET_CONSTANT);
				tess.addVertex(x,     y + 1, z - OFFSET_CONSTANT);
				tess.addVertex(x + 1, y + 1, z - OFFSET_CONSTANT);
				tess.addVertex(x + 1, y,     z - OFFSET_CONSTANT);
			}
			
			case SOUTH: {
				tess.addVertex(x + 1, y,     z + 1 + OFFSET_CONSTANT);
				tess.addVertex(x + 1, y + 1, z + 1 + OFFSET_CONSTANT);
				tess.addVertex(x,     y + 1, z + 1 + OFFSET_CONSTANT);
				tess.addVertex(x,     y,     z + 1 + OFFSET_CONSTANT);
			}
			
			case WEST: {
				tess.addVertex(x - OFFSET_CONSTANT, y,     z + 1);
				tess.addVertex(x - OFFSET_CONSTANT, y + 1, z + 1);
				tess.addVertex(x - OFFSET_CONSTANT, y + 1, z);
				tess.addVertex(x - OFFSET_CONSTANT, y,     z);
			}
			
			default: break;
		}
		
		tess.draw();
	}
	
}
