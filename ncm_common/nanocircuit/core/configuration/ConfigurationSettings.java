package nanocircuit.core.configuration;

public class ConfigurationSettings {

	/* Magnetite spawn settings */
	public static boolean MAGNETITE_ENABLED;
	public static final String MAGNETITE_ENABLED_CONFIGNAME = "magnetite.enabled";
	public static final boolean MAGNETITE_ENABLED_DEFAULT = true;
	
	public static int MAGNETITE_MAX_Y_LEVEL;
	public static final String MAGNETITE_MAX_Y_LEVEL_CONFIGNAME = "magnetite.max_y_level";
	public static final int MAGNETITE_MAX_Y_LEVEL_DEFAULT = 64;
	
	public static int MAGNETITE_SPAWN_AMOUNT;
	public static final String MAGNETITE_SPAWN_AMOUNT_CONFIGNAME = "magnetite.spawn_amount";
	public static final int MAGNETITE_SPAWN_AMOUNT_DEFAULT = 8;
	
	public static int MAGNETITE_SPAWN_RARITY;
	public static final String MAGNETITE_SPAWN_RARITY_CONFIGNAME = "magnetite.spawn_rarity";
	public static final int MAGNETITE_SPAWN_RARITY_DEFAULT = 8;

	/* Nickel spawn settings */
	public static boolean NICKEL_ENABLED;
	public static final String NICKEL_ENABLED_CONFIGNAME = "nickel.enabled";
	public static final boolean NICKEL_ENABLED_DEFAULT = true;
	
	public static int NICKEL_MAX_Y_LEVEL;
	public static final String NICKEL_MAX_Y_LEVEL_CONFIGNAME = "nickle.max_y_level";
	public static final int NICKEL_MAX_Y_LEVEL_DEFAULT = 64;
	
	public static int NICKEL_SPAWN_AMOUNT;
	public static final String NICKEL_SPAWN_AMOUNT_CONFIGNAME = "nickle.spawn_amount";
	public static final int NICKEL_SPAWN_AMOUNT_DEFAULT = 8;
	
	public static int NICKEL_SPAWN_RARITY;
	public static final String NICKEL_SPAWN_RARITY_CONFIGNAME = "nickle.spawn_rarity";
	public static final int NICKEL_SPAWN_RARITY_DEFAULT = 8;

	/* Magnetized armor ability settings */
	public static double MAGNETIC_RANGE;
	public static final String MAGNETIC_RANGE_CONFIGNAME = "magnetic_armor.range";
	public static final double MAGNETIC_RANGE_DEFAULT = 3D;
	
	public static double MAGNETIC_ATTRACTION_SPEED;
	public static final String MAGNETIC_ATTRACTION_SPEED_CONFIGNAME = "magnetic_armor.speed";
	public static final double MAGNETIC_ATTRACTION_SPEED_DEFAULT = 0.1D;
	
}
