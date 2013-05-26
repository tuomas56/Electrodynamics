package electrodynamics.core;

import java.io.File;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import electrodynamics.Electrodynamics;
import electrodynamics.core.handler.ConnectionHandler;
import electrodynamics.core.handler.GuiHandler;
import electrodynamics.core.lang.EDLanguage;
import electrodynamics.lib.client.FXType;
import electrodynamics.module.ModuleManager;
import electrodynamics.recipe.CraftingManager;
import net.minecraft.world.World;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		// Language manager initialization
		Electrodynamics.instance.languageManager = EDLanguage.getInstance();
		// Crafting manager initialization
		Electrodynamics.instance.craftingManager = new CraftingManager();
		// Connection handler registration
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
		
		ModuleManager.preInit();
	}

	public void init(FMLInitializationEvent event) {
		// GuiHandler registration
		NetworkRegistry.instance().registerGuiHandler(Electrodynamics.instance, new GuiHandler());
		
		ModuleManager.init();
	}

	public void postInit(FMLPostInitializationEvent event) {
		ModuleManager.postInit();
	}
	
	public void setKeyBinding(String name, int value, boolean repeats) {

	}

	public void registerKeyBindings() {

	}

	public void handleFXPacket(FXType type, double x, double y, double z, int[] extraInfo) {
		
	}
	
	public void handleSoundPacket(String sound, double x, double y, double z) {
		
	}
	
	public void handleSFXPacket(int sfx, double x, double y, double z) {
		
	}
	
	public void addBlockDestroyParticles(int x, int y, int z, int blockID, int meta) {

	}

	public void addEnderParticles(double x, double y, double z) {
		
	}
	
	public void addLightningFX(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration) {
		
	}
	
}
