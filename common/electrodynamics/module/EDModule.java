package electrodynamics.module;

import java.util.EnumSet;

import electrodynamics.module.ModuleManager.Module;

public abstract class EDModule {

	public abstract void preInit();
	
	public abstract void init();
	
	public abstract void postInit();
	
	public EnumSet<Module> dependencies() {
		return EnumSet.of(Module.CORE);
	}
	
	public boolean canLoad() {
		return true;
	}
	
	public String failLoadReason() {
		return "Generic Fail Reason";
	}
	
}
