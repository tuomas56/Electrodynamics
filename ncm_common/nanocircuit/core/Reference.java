package nanocircuit.core;

public class Reference 
{
	public static final String MOD_ID = "NCM";
	public static final String MOD_NAME = "NanoCircuitMod";
	public static final String VERSION = "0.0.1";
	public static final String BLOCK_TEXTURE = "/nanocircuit/art/sprites/block.png";
	public static final String ITEM_TEXTURE = "/nanocircuit/art/sprites/item.png";
	
	public static class ORE_META
	{
		public static final int MAGNETITE = 0;
		public static final int NICKEL = 1;
		public static final int AMOUNT = 2;
	}
	
	public static class STORAGE_META
	{
		public static final int LODESTONE = 0;
		public static final int AMOUNT = 1;
	}
	
	public static class COMPONENT_META
	{
		public static final int MAGNETITE_CHUNK = 0;
		public static final int MAGNETITE_DUST = 1;
		public static final int LODESTONE_DUST = 2;
		public static final int LODESTONE_INGOT = 3;
		public static final int IRON_ROD = 4;
		public static final int IRON_FANBLADE = 5;
		public static final int FERRITE_MAGNET = 6;
		public static final int AMOUNT = 7;
	}
	
	public static class PCB_META
	{
		public static final int BASIC_PCB = 0;
		public static final int AMOUNT = 1;
	}

	public static class GUI_ID
	{
		public static final int CONVECTION_OVEN = 0;
		public static final int AMOUNT = 1;
	}
	
}
