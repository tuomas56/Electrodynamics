package electrodynamics.module;

import electrodynamics.block.BlockHandler;

public class EDModuleLogic extends EDModule {

	@Override
	public void preInit() {
		BlockHandler.createBlockRedWire();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

}
