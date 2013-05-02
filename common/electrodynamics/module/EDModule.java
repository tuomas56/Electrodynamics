package electrodynamics.module;

import java.util.EnumSet;

import electrodynamics.module.ModuleManager.Module;

public abstract class EDModule {

	public abstract void preInit();
	
	public abstract void init();
	
	public abstract void postInit();
	
	public abstract EnumSet<Module> dependencies();
	
	public abstract boolean canLoad();
	
	public abstract String failLoadReason();
	
}
