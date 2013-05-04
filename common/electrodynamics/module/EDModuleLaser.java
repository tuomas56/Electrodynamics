package electrodynamics.module;

import electrodynamics.Electrodynamics;
import electrodynamics.block.BlockHandler;

public class EDModuleLaser extends EDModule {

	@Override
	public void preInit() {
		BlockHandler.createNewLaserEmitter();
	}

	@Override
	public void init() {
		Electrodynamics.proxy.registerEntities();
	}

	@Override
	public void postInit() {
		
	}

}
