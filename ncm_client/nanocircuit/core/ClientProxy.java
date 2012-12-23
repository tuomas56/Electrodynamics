package nanocircuit.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import nanocircuit.core.Reference;
import nanocircuit.core.CommonProxy;

import nanocircuit.tile.TileConvectionOven;
import nanocircuit.gui.GuiConvectionOven;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case Reference.GUI_ID.CONVECTION_OVEN:
				return new GuiConvectionOven(player.inventory, (TileConvectionOven)world.getBlockTileEntity(x, y, z));	
		}
		return null;
	}
	
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture(Reference.BLOCK_TEXTURE);
		MinecraftForgeClient.preloadTexture(Reference.ITEM_TEXTURE);
	}
}
