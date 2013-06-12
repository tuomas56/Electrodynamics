package electrodynamics.block.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import electrodynamics.block.EDBlocks;
import electrodynamics.lib.core.Strings;

public class ItemBlockWormwood extends ItemBlock {

	public ItemBlockWormwood(int i) {
		super(i);
		setHasSubtypes(true);
	}
	
	@Override
	public void registerIcons(IconRegister icon) {}
	
	@Override
    public Icon getIconFromDamage(int damage) {
        return EDBlocks.blockWormwood.getIcon( 0, damage );
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
