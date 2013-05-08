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
		File resDestDir = new File(mc.mcDataDir, "resources/" + ModInfo.GENERIC_MOD_ID.toLowerCase() + "/");
		if (!resDestDir.exists()) resDestDir.mkdir();
		
		File resFile = new File(resDestDir, resName);
		System.out.println(resFile.getAbsolutePath());
		if (!resFile.exists()) {
			InputStream streamIn = Electrodynamics.class.getResourceAsStream(Sound.SOUND_BASE + resName);
			BufferedOutputStream streamOut = new BufferedOutputStream(new FileOutputStream(resFile));
			byte[] buffer = new byte[1024];
			for (int len = 0; (len = streamIn.read(buffer)) >= 0;) {
				streamOut.write(buffer, 0, len);
			}
			streamIn.close();
			streamOut.close();
		}
		if (resFile.length() < 3L) throw new IOException();
		return resFile;
	}

}
