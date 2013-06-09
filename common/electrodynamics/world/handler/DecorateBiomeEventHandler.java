package electrodynamics.world.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import electrodynamics.block.EDBlocks;
import electrodynamics.util.BlockUtil;

public class DecorateBiomeEventHandler {

	public static final Type[] WORMWOOD_VALID_BIOMES = new Type[] {Type.PLAINS, Type.SWAMP, Type.HILLS, Type.FOREST, Type.JUNGLE, Type.MOUNTAIN};
	public static final Type[] DRY_WORMWOOD_VALID_BIOMES = new Type[] {Type.DESERT, Type.WASTELAND};
	
	@ForgeSubscribe
	public void onDecorate(final Decorate event) {
		if (event.type == DecorateBiomeEvent.Decorate.EventType.FLOWERS) {
			handleWormwood(event.world, event.chunkX, event.chunkZ);
		}
	}

	public void handleWormwood(World world, int chunkX, int chunkZ) {
		BiomeGenBase biome = world.getBiomeGenForCoords(chunkX, chunkZ);
		List<Type> biomeTypes = Arrays.asList(BiomeDictionary.getTypesForBiome(biome));
		
		for (Type type : WORMWOOD_VALID_BIOMES) {
			if (biomeTypes.contains(type)) {
				generateWormwood(world, chunkX, chunkZ, 0);
			}
		}
		
		for (Type type : DRY_WORMWOOD_VALID_BIOMES) {
			if (biomeTypes.contains(type)) {
				generateWormwood(world, chunkX, chunkZ, 1);
			}
		}
	}
	
	public void generateWormwood(World world, int chunkX, int chunkZ, int meta) {
		final int COUNT = 16;
		
		Random random = new Random();
		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		int y = BlockUtil.getFirstUncoveredYPos(world, x, z);
		
		for (int i=0; i<COUNT; i++) {
			x = x + random.nextInt(8) - random.nextInt(8);
			y = y + random.nextInt(4) - random.nextInt(4);
			z = z + random.nextInt(8) - random.nextInt(8);

			if (world.isAirBlock(x, y, z) && (!world.provider.hasNoSky || y < 127) && Block.blocksList[EDBlocks.blockWormwood.blockID].canBlockStay(world, x, y, z)) {
				world.setBlock(x, y, z, EDBlocks.blockWormwood.blockID, meta, 2);
			}
		}
	}
	
}
