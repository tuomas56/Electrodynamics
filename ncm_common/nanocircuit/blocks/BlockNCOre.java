package nanocircuit.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.*;

import nanocircuit.core.Reference;
import nanocircuit.NanoCircuitMod;
import nanocircuit.items.ItemManager;

public class BlockNCOre extends Block
{
	
	public BlockNCOre(int i)
    {
        super(i, Material.rock);
        setHardness(3F);
        setResistance(5F);
        setCreativeTab(net.minecraft.src.CreativeTabs.tabBlock);
    }
    
	@Override
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return j;
    }
    
	@Override
    public int idDropped(int meta, Random random, int j)
    {
		if(meta == Reference.BLOCK_MAGNETITE_META)
		{
			return ItemManager.itemComponent.shiftedIndex;
		}
		else
		{
			return blockID;
		}
    }
	
	@Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if (meta == Reference.BLOCK_MAGNETITE_META)
        {
        	if(NanoCircuitMod.instance.isIC2Installed)
        	{
        		//Drop 1-2 (+fortune) chunks
        		return 1 + random.nextInt(2) + random.nextInt(fortune);
        	}
        	//Drop 4-5 (+fortune+1) dusts
            return 4 + random.nextInt(2) + random.nextInt(fortune + 1);
        }
        else
        {
            return 1;
        }
    }
	
	@Override
    public int damageDropped(int meta)
    {
		if(meta == Reference.BLOCK_MAGNETITE_META)
		{
			if(NanoCircuitMod.instance.isIC2Installed)
			{
				return Reference.ITEM_MAGNETITE_CHUNK_META;
			}
			return Reference.ITEM_MAGNETITE_DUST_META;
		}
		else
		{
			return meta;
		}
    }
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public String getTextureFile () 
	{
		return Reference.BLOCK_TEXTURE;
	}

}
