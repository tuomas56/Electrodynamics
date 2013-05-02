package electrodynamics;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import electrodynamics.core.block.CoreBlockHandler;
import electrodynamics.core.client.render.RenderThermalOverlay;
import electrodynamics.core.configuration.ConfigurationHandler;
import electrodynamics.core.core.CommonProxy;
import electrodynamics.core.core.helper.HeatHelper;
import electrodynamics.core.item.ItemHandler;
import electrodynamics.core.lib.ModInfo;

@Mod(modid = "ED|Core", name = "Electrodynamics - Core", version = ModInfo.VERSION, dependencies = "after:IC2")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ElectroDynamicsCore {
	@Instance("ED|Core")
	public static ElectroDynamicsCore instance;
	@SidedProxy(clientSide = "electrodynamics.core.core.ClientProxy", serverSide = "electrodynamics.core.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.handleConfig(new File(event.getModConfigurationDirectory(), ModInfo.CORE_CONFIG));
		
		ItemHandler.initializeItems();
		CoreBlockHandler.initializeBlocks();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		HeatHelper.initializeMapping();
		MinecraftForge.EVENT_BUS.register(new RenderThermalOverlay());
		proxy.registerTileEntities();
	}

}
