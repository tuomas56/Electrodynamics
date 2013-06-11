package electrodynamics.item;

import electrodynamics.core.CreativeTabED;
import electrodynamics.lib.core.ModInfo;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSeed extends Item  {

	public ItemSeed(int itemID) {
		super( itemID );
		setCreativeTab( CreativeTabED.resource );
	}

	 // todo: on item use: plant seed.

	@Override
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon( ModInfo.ICON_PREFIX + "plant/wormwoodSeeds" );
    }

}
