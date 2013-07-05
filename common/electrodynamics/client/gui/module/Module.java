package electrodynamics.client.gui.module;

import scala.collection.parallel.ParIterableLike.Min;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import electrodynamics.client.render.util.RenderUtil;

public class Module {

	public int x;
	public int y;
	public int w;
	public int h;
	
	public String uuid;

	public ModuleManager manager;
	
	public Module(String uuid, int x, int y, int w, int h) {
		this.uuid = uuid;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public String[] getTooltip() {
		return null;
	}
	
	public void onClicked() {
		
	}
	
	public void onRender(int mouseX, int mouseY, MouseState state) {
		if (state == MouseState.MOUSE_ON) {
			String[] tooltipText = getTooltip();
			
			if (tooltipText != null && tooltipText.length > 0) {
				RenderUtil.drawTooltip(tooltipText, mouseX, mouseY, manager.parent.width, manager.parent.height, Minecraft.getMinecraft().fontRenderer);
			}
		}
	}
	
	public enum MouseState {
		MOUSE_ON,
		MOUSE_OFF;
	}
	
}
