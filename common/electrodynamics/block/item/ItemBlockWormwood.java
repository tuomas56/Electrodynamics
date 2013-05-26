package electrodynamics.block.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;

public class ItemBlockWormwood extends ItemBlock {

	public Icon[] textures;
	
	public ItemBlockWormwood(int i) {
		super(i);
		setHasSubtypes(true);
	}
	
	@Override
	public void registerIcons(IconRegister icon) {
		textures = new Icon[2];

		textures[0] = icon.registerIcon(ModInfo.ICON_PREFIX + "world/plant/plantWormseed");
		textures[1] = icon.registerIcon(ModInfo.ICON_PREFIX + "world/plant/plantWormseedDried");
	}
	
	@Override
    public Icon getIconFromDamage(int par1)
    {
        return textures[par1];
    }
	
	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return Strings.BLOCK_WORMWOOD;
	}
	
}
