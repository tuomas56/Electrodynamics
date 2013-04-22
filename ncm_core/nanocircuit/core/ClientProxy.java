package nanocircuit.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch( ID ) {
			default:
				return null;
		}
	}

	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture( Reference.BLOCK_TEXTURE );
		MinecraftForgeClient.preloadTexture( Reference.ITEM_TEXTURE );
	}

}
