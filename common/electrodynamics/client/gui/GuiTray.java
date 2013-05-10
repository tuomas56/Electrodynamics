package electrodynamics.client.gui;

import org.lwjgl.opengl.GL11;

import electrodynamics.inventory.container.ContainerTray;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.StatCollector;

public class GuiTray extends GuiContainer {

	public ContainerTray container;
	
	public GuiTray(ContainerTray container) {
		super(container);
		
		this.container = container;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = "Tray";
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/gui/trap.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
}
