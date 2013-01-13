package nanocircuit.world;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.NanoCircuitCore;
import nanocircuit.core.Reference;
import nanocircuit.core.Config;

public class BlockOre extends Block
{
	
	public BlockOre(int i)
    {
        super(i, Material.rock);
        setHardness(3F);
        setResistance(5F);
        setCreativeTab(NanoCircuitCore.tabsNCM);
    }
    
	@Override
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return j;
    }
    
	@Override
    public int idDropped(int meta, Random random, int j)
    {
		if(meta == Reference.ORE_META.MAGNETITE)
		{
			return NanoCircuitCore.itemComponent.shiftedIndex;
		}
		else
		{
			return blockID;
		}
    }
	
	@Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == Reference.ORE_META.MAGNETITE)
        {
        	if(Config.MODS_INSTALLED.IC2)
        	{
        		//Drop 1-2 (add fortune-1) chunks
        		return 1 + random.nextInt(2) + random.nextInt(fortune+1);
        	}
        	//Drop 4-5 (add fortune-1) dusts
            return 4 + random.nextInt(2) + random.nextInt(fortune+1);
        }
        else
        {
            return 1;
        }
    }
	
	@Override
    public int damageDropped(int meta)
    {
		if(meta == Reference.ORE_META.MAGNETITE)
		{
			if(Config.MODS_INSTALLED.IC2)
			{
				return Reference.COMPONENT_META.MAGNETITE_CHUNK;
			}
			return Reference.COMPONENT_META.MAGNETITE_DUST;
		}
		else
		{
			return meta;
		}
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) 
	{
		for (int ix = 0; ix < Reference.ORE_META.AMOUNT; ix++) 
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
