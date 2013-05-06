package electrodynamics.core;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import electrodynamics.control.KeyBindingHelper;
import electrodynamics.control.KeybindingHandler;

public class ClientProxy extends CommonProxy {

	public void preInitClient() {
		
	}
	
	public void initClient() {
		
	}
	
	@Override
	public void setKeyBinding(String name, int value, boolean repeats) {
		KeyBindingHelper.addKeyBinding(name, value, repeats);
	}
	
	@Override
	public void registerKeyBindings() {
		KeyBindingRegistry.registerKeyBinding(new KeybindingHandler());
	}
	
}
