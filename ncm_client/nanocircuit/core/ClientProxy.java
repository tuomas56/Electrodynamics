package nanocircuit.core;

import net.minecraftforge.client.MinecraftForgeClient;
import nanocircuit.core.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture(Reference.BLOCK_TEXTURE);
		MinecraftForgeClient.preloadTexture(Reference.ITEM_TEXTURE);
	}

}
