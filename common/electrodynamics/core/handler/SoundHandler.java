package electrodynamics.core.handler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.Electrodynamics;
import electrodynamics.core.EDLogger;
import electrodynamics.lib.client.Sound;
import electrodynamics.lib.core.ModInfo;

public class SoundHandler {

	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent e) {
		String[] soundFiles = new String[] { 
			"block/voidstoneAmbient.ogg", 
			"block/oreCrumble.ogg", 
			"block/treeTap.ogg",
			"block/barkCut1.ogg",
			"block/barkCut2.ogg",
			"block/melonSquish.ogg"
		};

		for (int i = 0; i < soundFiles.length; i++) {
			try {
//				File file = extractAndLoadResource(Minecraft.getMinecraft(), soundFiles[i]);
//				e.manager.soundPoolSounds.addSound(ModInfo.GENERIC_MOD_ID.toLowerCase() + "/" + soundFiles[i], file);
				
				e.manager.soundPoolSounds.addSound(ModInfo.GENERIC_MOD_ID.toLowerCase() + "/" + soundFiles[i]);
				EDLogger.info("Loaded sound: " + soundFiles[i]);
			} catch (Exception ex) {
				EDLogger.warn("Failed to load sound: " + soundFiles[i] + " - Reason: " + ex.getLocalizedMessage());
			}
		}
	}

}
