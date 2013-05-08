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
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Sound;

public class SoundHandler {

	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent e) {
		String[] soundFiles = new String[] { "block/voidstoneAmbient.ogg" };
		for (int i = 0; i < soundFiles.length; i++) {
			try {
				File file = extractAndLoadResource(Minecraft.getMinecraft(), soundFiles[i]);

				e.manager.soundPoolSounds.addSound(ModInfo.GENERIC_MOD_ID.toLowerCase() + "/" + soundFiles[i], file);
				EDLogger.info("Loaded sound: " + soundFiles[i]);
			} catch (Exception ex) {
				EDLogger.warn("Failed to load sound: " + soundFiles[i] + " - Reason: " + ex.getLocalizedMessage());
			}
		}
	}

	@SideOnly(Side.CLIENT)
	private File extractAndLoadResource(Minecraft mc, String resName) throws Exception {
		String MC_PATH_STRING = Minecraft.getMinecraftDir().getAbsolutePath();
		MC_PATH_STRING = MC_PATH_STRING.substring(0, MC_PATH_STRING.length() - 1);
		File resDestDir = new File(MC_PATH_STRING, "resources/" + ModInfo.GENERIC_MOD_ID.toLowerCase() + "");

		if (!resDestDir.exists()) {
			resDestDir.mkdirs();
		}
		
		File blockSoundDir = new File(resDestDir, "block");
		if (!blockSoundDir.exists()) {
			blockSoundDir.mkdir();
		}
		
		File resDest = new File(resDestDir, resName);
		
		if (!resDest.exists()) {
			resDest.createNewFile();
			
			InputStream fileIn = Electrodynamics.class.getResourceAsStream(Sound.SOUND_BASE + resName);
			BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(resDest));
			
			byte[] fileBuffer = new byte[1024];
			for (int i=0; (i = fileIn.read(fileBuffer))>= 0;) {
				fileOut.write(fileBuffer, 0, i);
			}
			
			fileIn.close();
			fileOut.close();
		}
		
		if (resDest.length() < 3L) {
			throw new IOException();
		}
		
		return resDest;
	}

}
