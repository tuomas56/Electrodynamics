package nanocircuit.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import nanocircuit.core.Reference;
import nanocircuit.items.ItemManager;

public class BlockCleanDoor extends BlockDoor
{
	public BlockCleanDoor(int par1)
	{
		super(par1, Material.iron);
		this.blockIndexInTexture = 32;
	}
	
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		int var10 = this.getFullMetadata(par1World, par2, par3, par4);
		int var11 = var10 & 7;
		var11 ^= 4;

		if ((var10 & 8) == 0)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, var11);
			par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
		}
		else
		{
			par1World.setBlockMetadataWithNotify(par2, par3 - 1, par4, var11);
			par1World.markBlockRangeForRenderUpdate(par2, par3 - 1, par4, par2, par3, par4);
		}

		par1World.playAuxSFXAtEntity(par5EntityPlayer, 1003, par2, par3, par4, 0);
		return true;
    }
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return ItemManager.itemDoor.shiftedIndex;
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return ItemManager.itemDoor.shiftedIndex;
    }

	@Override
	public String getTextureFile () 
	{
		return Reference.BLOCK_TEXTURE;
	}
}
