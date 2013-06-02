package electrodynamics.addons.computercraft;

import dan200.turtle.api.TurtleAPI;
import electrodynamics.addons.EDAddon;

public class EDAddonComputerCraft extends EDAddon {

	public void init() {
		TurtleAPI.registerUpgrade(new TurtleHammer());
	}
	
	@Override
	public String getModDependency() {
		return "ComputerCraft";
	}

}
