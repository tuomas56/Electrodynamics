package nanocircuit.core;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.NanoCircuitCore;
import nanocircuit.core.Reference;

public class ItemComponent extends Item
{
	public ItemComponent(int i)
	{
		super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(NanoCircuitCore.tabsNCM);
	}
	
	@Override
	public int getIconFromDamage(int meta)
	{
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		for(int i = 0; i < Reference.COMPONENT_META.AMOUNT; i++)
			par3List.add(new ItemStack(par1, 1, i));
    }
	
	@Override
    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.COMPONENT_META.MAGNETITE_CHUNK:
                return "item.chunkMagnetite";
                
            case Reference.COMPONENT_META.MAGNETITE_DUST:
                return "item.dustMagnetite";
                
            case Reference.COMPONENT_META.LODESTONE_DUST:
                return "item.dustLodestone";
                
            case Reference.COMPONENT_META.LODESTONE_INGOT:
                return "item.ingotLodestone";    
                
            case Reference.COMPONENT_META.IRON_ROD:
                return "item.rodIron";    
                
            case Reference.COMPONENT_META.IRON_FANBLADE:
                return "item.fanbladeIron";    
                
            case Reference.COMPONENT_META.FERRITE_MAGNET:
                return "item.magnetFerrite";       
        }

        throw new IndexOutOfBoundsException();
    }
	
	@Override
    public String getTextureFile()
    {
        return Reference.ITEM_TEXTURE;
    }

}
