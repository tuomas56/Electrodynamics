package electrodynamics;

import java.io.File;
import java.lang.reflect.Field;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.CoreModManager;
import electrodynamics.api.IEDApi;
import electrodynamics.api.crafting.ICraftingManager;
import electrodynamics.configuration.ConfigurationHandler;
import electrodynamics.core.CommonProxy;
import electrodynamics.core.handler.IconHandler;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.network.PacketHandler;
import electrodynamics.recipe.manager.CraftingManager;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.GENERIC_MOD_ID, version = ModInfo.VERSION, dependencies = ModInfo.DEPENDENCIES)
@NetworkMod(channels = { ModInfo.GENERIC_MOD_ID }, clientSideRequired = false, serverSideRequired = false, packetHandler = PacketHandler.class)
public class Electrodynamics implements IEDApi {
	@Instance(ModInfo.MOD_ID)
	public static Electrodynamics instance;

	@SidedProxy(clientSide = "electrodynamics.core.ClientProxy", serverSide = "electrodynamics.core.CommonProxy")
	public static CommonProxy proxy;

	public boolean showOptifineError = false;
	public boolean obfuscated;
	public File configFolder;
	public CraftingManager craftingManager;
	public EDLanguage languageManager;

	public static Material gas = new Material(MapColor.airColor);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Electrodynamics.instance.configFolder = new File(event.getModConfigurationDirectory(), ModInfo.GENERIC_MOD_ID);
		ConfigurationHandler.handleConfig(new File(configFolder, ModInfo.GENERIC_MOD_ID + ".cfg"));

		try {
			Field deobfBool;
			deobfBool = CoreModManager.class.getDeclaredField("deobfuscatedEnvironment");
			deobfBool.setAccessible(true);
			obfuscated = !deobfBool.getBoolean(Boolean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	@Override
	public ICraftingManager getCraftingManager() {
		return craftingManager;
	}

}
