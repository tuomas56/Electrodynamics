package nanocircuit.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import nanocircuit.container.ContainerConvectionOven;
import nanocircuit.tile.TileConvectionOven;

public class GuiConvectionOven extends GuiContainer
{
    public GuiConvectionOven (InventoryPlayer inventoryPlayer, TileConvectionOven tileEntity) 
    {
    	//the container is instanciated and passed to the superclass for handling
    	super(new ContainerConvectionOven(inventoryPlayer, tileEntity));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) 
    {
    	//draw text and stuff here
    	//the parameters for drawString are: string, x, y, color
    	fontRenderer.drawString("Tiny", 8, 6, 4210752);
    	//draws "Inventory" or your regional equivalent
    	fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) 
    {
    	int texture = mc.renderEngine.getTexture("/your/texture/path/here.png");
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.mc.renderEngine.bindTexture(texture);
    	int x = (width - xSize) / 2;
    	int y = (height - ySize) / 2;
    	this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
