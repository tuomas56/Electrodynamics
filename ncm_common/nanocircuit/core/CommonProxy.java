package nanocircuit.core;

import cpw.mods.fml.common.network.IGuiHandler;

import nanocircuit.container.ContainerConvectionOven;
import nanocircuit.tile.TileConvectionOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


public class CommonProxy implements IGuiHandler
{	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case Reference.GUI_CONVECTION_OVEN_ID:
				return new ContainerConvectionOven(player.inventory, (TileConvectionOven)world.getBlockTileEntity(x, y, z));	
		}
		return null;
	}
	  
	public void registerRenderers()
	{
	}
}