package nanocircuit.world;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.core.Reference;
import nanocircuit.NanoCircuitCore;

public class BlockStorage extends Block
{
	public BlockStorage(int i)
    {
        super(i, Material.iron);
        setHardness(3F);
        setResistance(5F);
        setCreativeTab(NanoCircuitCore.tabsNCM);
    }
    
	@Override
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return 18 + j;
    }
    
	@Override
    public int idDropped(int meta, Random random, int j)
    {
		return blockID;
    }
	
	@Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
		return 1;
    }
	
	@Override
    public int damageDropped(int meta)
    {
		return meta;
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
	{
		for (int ix = 0; ix < 1; ix++) 
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public String getTextureFile() 
	{
		return Reference.BLOCK_TEXTURE;
	}
	
}
