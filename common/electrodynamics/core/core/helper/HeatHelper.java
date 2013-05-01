package electrodynamics.core.core.helper;

import java.util.HashMap;
import java.util.Map;

import electrodynamics.core.lib.HeatValue;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class HeatHelper {

	public static Map<Integer, HeatValue> heatMapping = new HashMap<Integer, HeatValue>();
	
	public static void setBlockHeatValue(Block block, HeatValue value) {
		heatMapping.put(block.blockID, value);
	}
	
	public static HeatValue getBlockHeatValue(int id) {
		return heatMapping.containsKey(id) == true ? heatMapping.get(id) : HeatValue.TEPID;
	}
	
	public static HeatValue getBlockHeatValue(World world, int x, int y, int z) {
		return getBlockHeatValue(world.getBlockId(x, y, z));
	}
	
	public static void initializeMapping() {
		setBlockHeatValue(Block.furnaceBurning, HeatValue.HOT);
	}
	
}
