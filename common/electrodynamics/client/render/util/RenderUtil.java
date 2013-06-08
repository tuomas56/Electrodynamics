package electrodynamics.client.render.util;

import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class RenderUtil {
	public static void translateToWorldCoords(Entity entity, float partialTicks)
	{
		double X = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks);
	    double Y = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks);
	    double Z = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks);
	    
	    GL11.glTranslatef(-(float)X, -(float)Y, -(float)Z);
	}

}
