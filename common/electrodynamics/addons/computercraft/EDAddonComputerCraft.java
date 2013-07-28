package electrodynamics.addons.computercraft;

import java.io.File;

import dan200.turtle.api.TurtleAPI;

import net.minecraftforge.common.Configuration;
import electrodynamics.Electrodynamics;
import electrodynamics.addons.EDAddon;
import electrodynamics.lib.core.ModInfo;

public class EDAddonComputerCraft extends EDAddon {

	public static final String CATEGORY_CC = "addon.computercraft";
	
	public static boolean canUseTable = true;
	public static boolean canFormMBS = true;
	public static boolean canTakeFromTable = true;
	
	@Override
	public void init() {
		Configuration config = new Configuration(new File(Electrodynamics.instance.configFolder, ModInfo.GENERIC_MOD_ID + ".cfg"));
		EDAddonComputerCraft.canUseTable = config.get(CATEGORY_CC, "canUseTable", true, "Can the turtle smash items via smashing table.").getBoolean(true);
		EDAddonComputerCraft.canFormMBS = config.get(CATEGORY_CC, "canFormMBS", true, "Can the turtle form multi-block structures.").getBoolean(true);
		EDAddonComputerCraft.canTakeFromTable = config.get(CATEGORY_CC, "canUseTable", true, "Will the turtle automatically take the smashing result from the table.").getBoolean(true);
			
		TurtleAPI.registerUpgrade(new TurtleHammer());
	}

}