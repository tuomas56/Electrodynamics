package electrodynamics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import electrodynamics.core.handler.GuiHandler;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.inventory.container.ContainerTray;
import electrodynamics.lib.core.Strings;

public class GuiTray extends GuiElectrodynamics {

	public EntityPlayer player;
	
	public ContainerTray container;
	
	public GuiTray(EntityPlayer player, ContainerTray container) {
		super(container);
		
		this.container = container;
		this.player = player;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String s = EDLanguage.getInstance().translate(Strings.GUI_TRAY);
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(GuiHandler.GuiType.METAL_TRAY.texture.resource);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
}
