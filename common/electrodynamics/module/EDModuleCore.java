package electrodynamics.module;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;

import electrodynamics.Electrodynamics;
import electrodynamics.block.BlockHandler;
import electrodynamics.client.render.misc.RenderThermalOverlay;
import electrodynamics.configuration.ConfigurationHandler;
import electrodynamics.core.helper.HeatHelper;
import electrodynamics.item.ItemHandler;
import electrodynamics.lib.ModInfo;

public class EDModuleCore extends EDModule {

	//TODO Neatening up
	@Override
	public void preInit() {
		//Temp
		ConfigurationHandler.handleConfig(new File(Electrodynamics.instance.configFolder, ModInfo.CORE_CONFIG));
		
		Electrodynamics.proxy.registerKeyBindings();
		
		ItemHandler.initializeItems();
		BlockHandler.createBlockHoloPad();
		BlockHandler.createNewBlockTable();
	}

	@Override
	public void init() {
		HeatHelper.initializeMapping();
		MinecraftForge.EVENT_BUS.register(new RenderThermalOverlay());
		Electrodynamics.proxy.registerTileEntities();
		Electrodynamics.proxy.registerRenders();
	}

	@Override
	public void postInit() {
		
	}

}
