package nanocircuit.core;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.Configuration;

import java.io.File;

public class Config {

	public static Configuration config;

	public static class BLOCK_ID {
		public static int oreBlock;
		public static int storageBlock;
		public static int doorBlock;
		public static int glassBlock;
		public static int ironBlock;
		public static int machineBlock;
	}

	public static class ITEM_ID {
		public static int componentItem;
		public static int doorItem;
		public static int pcbItem;
	}

	public static class MODS_INSTALLED {
		public static boolean IC2;
	}

	public static void init(File file) {
		config = new Configuration( file );
		config.load();

		BLOCK_ID.oreBlock = config.getBlock( "BlockOre", 500 ).getInt();
		BLOCK_ID.storageBlock = config.getBlock( "BlockStorage", 501 ).getInt();
		BLOCK_ID.doorBlock = config.getBlock( "BlockDoor", 502 ).getInt();
		BLOCK_ID.glassBlock = config.getBlock( "BlockGlass", 503 ).getInt();
		BLOCK_ID.ironBlock = config.getBlock( "BlockIron", 504 ).getInt();

		BLOCK_ID.machineBlock = config.getBlock( "BlockMachine", 505 ).getInt();

		ITEM_ID.componentItem = config.getItem( "ItemComponent", 1500 ).getInt();
		ITEM_ID.doorItem = config.getItem( "ItemDoor", 1501 ).getInt();
		ITEM_ID.pcbItem = config.getItem( "ItemPCB", 1502 ).getInt();

		MODS_INSTALLED.IC2 = Loader.isModLoaded( "IC2" );

		config.save();
	}

}