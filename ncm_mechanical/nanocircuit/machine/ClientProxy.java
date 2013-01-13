package nanocircuit.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import nanocircuit.core.Reference;
import nanocircuit.core.Reference.GUI_ID;

public class ClientProxy extends CommonProxy
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case Reference.GUI_ID.CONVECTION_OVEN:
				return null;//new GuiConvectionOven(player.inventory, (TileConvectionOven)world.getBlockTileEntity(x, y, z));
			default:
				return null;
		}
	}
	
	@Override
	public void registerRenderers()
	{
	}

}
