package electrodynamics.addons.computercraft;

import electrodynamics.addons.EDAddon;

public class EDAddonComputerCraft extends EDAddon {

	public void init() {
		TurtleAPI.registerUpgrade(new SteelHammerUpgrade());
		TurtleAPI.registerUpgrade(new StoneHammerUpgrade());
	}
	
	@Override
	public String getModDependency() {
		return "ComputerCraft";
	}

}
