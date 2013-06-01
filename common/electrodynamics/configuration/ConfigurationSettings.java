package electrodynamics.configuration;

public class ConfigurationSettings {

	/* ElMag armor ability settings */
	public static double MAGNETIC_RANGE;
	public static final String MAGNETIC_RANGE_CONFIGNAME = "elmag_armor.range";
	public static final double MAGNETIC_RANGE_DEFAULT = 3D;
	
	public static double MAGNETIC_ATTRACTION_SPEED;
	public static final String MAGNETIC_ATTRACTION_SPEED_CONFIGNAME = "elmag_armor.speed";
	public static final double MAGNETIC_ATTRACTION_SPEED_DEFAULT = 0.8D;
	
	/* Audio settings */
	public static boolean VOIDSTONE_AMBIENT_SOUND;
	public static final String VOIDSTONE_AMBIENT_SOUND_NAME = "sound.voidstone.ambient";
	public static final boolean VOIDSTONE_AMBIENT_SOUND_DEFAULT = true;
	
	/* General settings */
	public static boolean SHOW_LOCALIZATION_ERRORS;
	public static final String SHOW_LOCALIZATION_ERRORS_NAME = "general.localization.show_errors";
	public static final boolean SHOW_LOCALIZATION_ERRORS_DEFAULT = false;
	
}
