package nanocircuit.core.util;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

public class RenderUtil {

	public static void translateToWorldCoords(Entity entity, float frame) {       
        double interpPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * frame;
        double interpPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * frame;
        double interpPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * frame;
        
        GL11.glTranslated(-interpPosX, -interpPosY, -interpPosZ);
    }
	
	public static void drawQuadOnFace(int x, int y, int z, double renderOffset, ForgeDirection face) {
		GL11.glBegin(GL11.GL_QUADS);
		
			switch(face) {
				case UP: {
					GL11.glVertex3d(x, y + renderOffset, z);
					GL11.glVertex3d(x, y + renderOffset, z + 1);
					GL11.glVertex3d(x + 1, y + renderOffset, z + 1);
					GL11.glVertex3d(x + 1, y + renderOffset, z);
				}
				
				case DOWN: {
					GL11.glVertex3d(x + 1, y - renderOffset, z);
					GL11.glVertex3d(x + 1, y - renderOffset, z + 1);
					GL11.glVertex3d(x, y - renderOffset, z + 1);
					GL11.glVertex3d(x, y - renderOffset, z);
				}
			case EAST:
				break;
			case NORTH:
				break;
			case SOUTH:
				break;
			case UNKNOWN:
				break;
			case WEST:
				break;
			default:
				break;
			}
		
		GL11.glEnd();
	}
	
}
