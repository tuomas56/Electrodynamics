package electrodynamics.module;

import electrodynamics.Electrodynamics;

public class EDModuleLaser extends EDModule {

	@Override
	public void preInit() {
		
	}

	@Override
	public void init() {
		Electrodynamics.proxy.registerEntities();
	}

	@Override
	public void postInit() {
		
	}

}
