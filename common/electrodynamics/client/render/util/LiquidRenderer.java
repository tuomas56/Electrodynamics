/**
 * Copyright (c) SpaceToad, 2011 http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License
 * 1.0, or MMPL. Please check the contents of the license located in
 * http://www.mod-buildcraft.com/MMPL-1.0.txt
 */
package electrodynamics.client.render.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

import org.lwjgl.opengl.GL11;

import electrodynamics.client.render.util.RenderEntityBlock.BlockInterface;

/**
 *
 * @author CovertJaguar <railcraft.wikispaces.com>
 */
public class LiquidRenderer {

	private static Map<String, int[]> flowingRenderCache = new HashMap<String, int[]>();
	private static Map<String, int[]> stillRenderCache = new HashMap<String, int[]>();

	private static final BlockInterface liquidBlock = new BlockInterface();

	@SuppressWarnings("serial")
	public static class LiquidTextureException extends RuntimeException {

		private final LiquidStack liquid;

		public LiquidTextureException(LiquidStack liquid) {
			super();
			this.liquid = liquid;
		}

		@Override
		public String getMessage() {
			String liquidName = LiquidDictionary.findLiquidName(liquid);
			if (liquidName == null) {
				liquidName = String.format("ID: %d Meta: %d", liquid.itemID, liquid.itemMeta);
			}
			return String.format("Liquid %s has no icon. Please contact the author of the mod the liquid came from.", liquidName);
		}
	}

	@SuppressWarnings("serial")
	public static class LiquidCanonException extends RuntimeException {

		private final LiquidStack liquid;

		public LiquidCanonException(LiquidStack liquid) {
			super();
			this.liquid = liquid;
		}

		@Override
		public String getMessage() {
			String liquidName = LiquidDictionary.findLiquidName(liquid);
			if (liquidName == null) {
				liquidName = String.format("ID: %d Meta: %d", liquid.itemID, liquid.itemMeta);
			}
			return String.format("Liquid %s is not registered with the Liquid Dictionary. Please contact the author of the mod the liquid came from.", liquidName);
		}
	}

	public static Icon getLiquidTexture(LiquidStack liquid) {
		if (liquid == null || liquid.itemID <= 0) {
			return null;
		}
		LiquidStack canon = liquid.canonical();
		if (canon == null) {
			throw new LiquidCanonException(liquid);
		}
		Icon icon = canon.getRenderingIcon();
		if (icon == null) {
			throw new LiquidTextureException(liquid);
		}
		return icon;
	}
	
	public static ResourceLocation getLiquidSheetResource(LiquidStack liquid) {
		return new ResourceLocation(getLiquidSheet(liquid));
	}
	
	public static String getLiquidSheet(LiquidStack liquid) {
		if (liquid == null || liquid.itemID <= 0) {
			return "/terrain.png";
		}
		LiquidStack canon = liquid.canonical();
		if (canon == null) {
			throw new LiquidCanonException(liquid);
		}
		return canon.getTextureSheet();
	}

	public static int[] getLiquidDisplayLists(LiquidStack liquid, World world, boolean flowing, float minX, float minZ, float maxX, float maxZ, int displayMax, String cacheName) {
		final int DISPLAY_MAX = displayMax + 1;
		
		if (liquid == null) {
			return null;
		}
		liquid = liquid.canonical();
		if(liquid == null){
			throw new LiquidCanonException(liquid);
		}
		Map<String, int[]> cache = flowing ? flowingRenderCache : stillRenderCache;
		int[] diplayLists = cache.get(liquid);
		if (diplayLists != null) {
			return diplayLists;
		}

		diplayLists = new int[DISPLAY_MAX];

		if (liquid.itemID < Block.blocksList.length && Block.blocksList[liquid.itemID] != null) {
			liquidBlock.baseBlock = Block.blocksList[liquid.itemID];
			if (!flowing) {
				liquidBlock.texture = getLiquidTexture(liquid);
			}
		} else if (Item.itemsList[liquid.itemID] != null) {
			liquidBlock.baseBlock = Block.waterStill;
			liquidBlock.texture = getLiquidTexture(liquid);
		} else {
			return null;
		}

		cache.put(cacheName, diplayLists);

		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_CULL_FACE);
		ItemStack stack = liquid.asItemStack();
		int color = stack.getItem().getColorFromItemStack(stack, 0);
		float c1 = (float) (color >> 16 & 255) / 255.0F;
		float c2 = (float) (color >> 8 & 255) / 255.0F;
		float c3 = (float) (color & 255) / 255.0F;
		GL11.glColor4f(c1, c2, c3, 1);
		for (int s = 0; s < DISPLAY_MAX; ++s) {
			diplayLists[s] = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(diplayLists[s], 4864 /*GL_COMPILE*/);

			liquidBlock.minX = minX;
			liquidBlock.minY = 0;
			liquidBlock.minZ = minZ;

			liquidBlock.maxX = maxX;
			liquidBlock.maxY = (float) s / (float) DISPLAY_MAX;
			liquidBlock.maxZ = maxZ;

			RenderEntityBlock.renderBlock(liquidBlock, world, 0, 0, 0, false, true);

			GL11.glEndList();
		}


		GL11.glColor4f(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);

		return diplayLists;
	}
}


