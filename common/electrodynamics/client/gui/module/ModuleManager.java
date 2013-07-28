package electrodynamics.client.gui.module;

import java.util.ArrayList;
import java.util.HashSet;

import electrodynamics.client.gui.GuiElectrodynamics;
import electrodynamics.core.EDLogger;

public class ModuleManager {

	private HashSet<String> moduleIDs = new HashSet<String>();
	public ArrayList<Module> modules = new ArrayList<Module>();
	
	public GuiElectrodynamics parent;
	
	public ModuleManager(GuiElectrodynamics parent) {
		this.parent = parent;
	}
	
	public void registerModule(Module module) {
		if (moduleIDs.contains(module.uuid)) {
			EDLogger.warn("A GUI tried to register a module with the ID of " + module.uuid + " but it already exists!");
			return;
		} else {
			module.manager = this;
			moduleIDs.add(module.uuid);
			modules.add(module);
		}
	}
	
}
