package electrodynamics.module;

import java.util.EnumSet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.module.ModuleManager.Module;

public abstract class EDModule {

	public void preInit() {
		
	}
	
	public void init() {
		
	}
	
	public void postInit() {
		
	}

	@SideOnly(Side.CLIENT)
	public void preInitClient() {
		
	}
	
	@SideOnly(Side.CLIENT)
	public void initClient() {
		
	}
	
	@SideOnly(Side.CLIENT)
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
