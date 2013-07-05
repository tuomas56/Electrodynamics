package electrodynamics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import electrodynamics.client.gui.module.Module;
import electrodynamics.client.gui.module.ModuleManager;
import electrodynamics.client.gui.module.Module.MouseState;

public abstract class GuiElectrodynamics extends GuiContainer {

	protected ModuleManager manager;
	
	public GuiElectrodynamics(Container container) {
		super(container);
		
		this.manager = new ModuleManager(this);
		this.initModules(manager);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float delta) {
		super.drawScreen(mouseX, mouseY, delta);
		
		for (Module module : manager.modules) {
			MouseState state = MouseState.MOUSE_OFF;
			if (this.isPointInRegion(module.x, module.y, module.w, module.h, mouseX, mouseY)) {
				state = MouseState.MOUSE_ON;
			}
			module.onRender(mouseX, mouseY, state);
		}
	}
	
	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float f, int i, int j);

	public void initModules(ModuleManager manager) {};
	
}
