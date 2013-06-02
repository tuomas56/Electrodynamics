package electrodynamics.computercraft;

import dan200.turtle.api.TurtleAPI;

public class CCMain implements ICCMain {

	private static CCMain instance;

	public static CCMain getInstance() {
		if (instance == null)
			new CCMain();
		return instance;
	}

	public CCMain() {
		instance = this;
	}

	@Override
	public void init() {
		TurtleAPI.registerUpgrade(new SteelHammerUpgrade());
		TurtleAPI.registerUpgrade(new StoneHammerUpgrade());
	}

}
