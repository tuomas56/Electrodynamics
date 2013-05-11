package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public enum Decorative {

	LIMESTONE(Strings.DECORATIVE_LIMESTONE, "blockLimestone"),
	LIMESTONE_BRICK(Strings.DECORATIVE_LIMESTONE_BRICK, "brickLimestone"),
	LIMESTONE_BRICKS(Strings.DECORATIVE_LIMESTONE_BRICKS, "bricksLimestone"),
	LIMESTONE_BRITTLE(Strings.DECORATIVE_LIMESTONE_BRITTLE, "brittleLimestone");
	
	public String unlocalizedName;
	public String textureName;

	private Decorative(String unlocalizedName, String textureName) {
		this.unlocalizedName = unlocalizedName;
		this.textureName = textureName;
	}

	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "decorative/" + textureName;
	}

	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_DECORATIVE_ID, 1, this.ordinal());
	}

	public static Decorative get(int ordinal) {
		return Decorative.values()[ordinal];
	}
	
}
