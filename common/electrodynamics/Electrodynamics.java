package electrodynamics;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import electrodynamics.core.CommonProxy;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.module.ModuleManager;
import electrodynamics.network.PacketHandler;

import java.io.File;

@Mod(modid = "ED", name = "Electrodynamics", version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
@NetworkMod(channels = { ModInfo.GENERIC_MOD_ID }, clientSideRequired = false, serverSideRequired = false, packetHandler = PacketHandler.class)
public class Electrodynamics {
	@Instance("ED")
	public static Electrodynamics instance;
	@SidedProxy(clientSide = "electrodynamics.core.ClientProxy", serverSide = "electrodynamics.core.CommonProxy")
	public static CommonProxy proxy;

	public File configFolder;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		this.configFolder = new File( event.getModConfigurationDirectory(), ModInfo.GENERIC_MOD_ID);

		ModuleManager.preInit();
		proxy.preInitClient();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		ModuleManager.init();
		proxy.initClient();
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		ModuleManager.postInit();
	}

}
