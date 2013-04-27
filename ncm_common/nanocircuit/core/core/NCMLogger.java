package nanocircuit.core.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class NCMLogger {

	public static Logger ncmLogger;
	
	static {
		ncmLogger = Logger.getLogger("NanoCircuit Mod");
		ncmLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level lvl, String msg) {
		ncmLogger.log(lvl, msg);
	}
	
	public static void info(String msg) {
		log(Level.INFO, msg);
	}
	
	public static void warn(String msg) {
		log(Level.WARNING, msg);
	}
	
}
