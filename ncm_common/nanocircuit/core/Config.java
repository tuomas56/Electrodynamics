package nanocircuit.core;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class Config {
	
	public static Configuration config;
	
	public static int oreBlockID;
	public static int storageBlockID;
	public static int doorBlockID;
	public static int glassBlockID;
	public static int ironBlockID;
	
	public static int machineBlockID;
	
	public static int componentItemID;
	public static int doorItemID;
	public static int pcbItemID;
	
	public static void init(File file)
	{
		config = new Configuration(file);
		config.load();
		
		oreBlockID = config.getBlock("BlockOre", 500).getInt();
		storageBlockID = config.getBlock("BlockStorage", 501).getInt();
		doorBlockID = config.getBlock("BlockDoor", 502).getInt();
		glassBlockID = config.getBlock("BlockGlass", 503).getInt();
		ironBlockID = config.getBlock("BlockIron", 504).getInt();
		
		machineBlockID = config.getBlock("BlockMachine", 505).getInt();
		
		componentItemID = config.getItem("ItemComponent", 1500).getInt();
		doorItemID = config.getItem("ItemDoor", 1501).getInt();
		pcbItemID = config.getItem("ItemPCB", 1502).getInt();
		
		config.save();
	}

}
