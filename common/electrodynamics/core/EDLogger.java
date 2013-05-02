package electrodynamics.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import electrodynamics.lib.ModInfo;

public class EDLogger {

	public static Logger edLogger;
	
	static {
		edLogger = Logger.getLogger(ModInfo.GENERIC_MOD_ID);
		edLogger.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level lvl, String msg) {
		edLogger.log(lvl, msg);
	}
	
	public static void info(String msg) {
		log(Level.INFO, msg);
	}
	
	public static void warn(String msg) {
		log(Level.WARNING, msg);
	}
	
	public static void fine(String msg) {
		log(Level.FINE, msg);
	}
	
}
