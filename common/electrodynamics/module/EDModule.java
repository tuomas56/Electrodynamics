package electrodynamics.module;

import java.util.EnumSet;

import electrodynamics.module.ModuleManager.Module;

public abstract class EDModule {

	public void preInit() {
		
	}
	
	public void init() {
		
	}
	
	public void postInit() {
		
	}

	public void preInitClient() {
		
	}
	
	public void initClient() {
		
	}
	
	public void postInitClient() {
		
	}
	
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
