package electrodynamics.module;

import java.util.EnumSet;

import electrodynamics.block.BlockHandler;
import electrodynamics.module.ModuleManager.Module;

public class EDModuleMachine extends EDModule {

	@Override
	public void preInit() {
		BlockHandler.createNewBlockMachine();
	}

	@Override
	public void init() {
		
	}

	@Override
	public void postInit() {
		
	}

	public EnumSet<Module> dependencies() {
		return EnumSet.of(Module.CORE, Module.WORLD);
	}
	
}
