package nanocircuit.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

import nanocircuit.NanoCircuitMod;
import nanocircuit.core.Reference;

public class ItemComponent extends Item
{
	public ItemComponent(int i)
	{
		super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(NanoCircuitMod.tabsNCM);
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
		for(int i = 0; i < Reference.ITEM_FERRITE_MAGNET_META+1; i++)
			par3List.add(new ItemStack(par1, 1, i));
    }
	
	@Override
    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.ITEM_MAGNETITE_CHUNK_META:
                return "item.chunkMagnetite";
                
            case Reference.ITEM_MAGNETITE_DUST_META:
                return "item.dustMagnetite";
                
            case Reference.ITEM_LODESTONE_DUST_META:
                return "item.dustLodestone";
                
            case Reference.ITEM_LODESTONE_INGOT_META:
                return "item.ingotLodestone";    
                
            case Reference.ITEM_IRON_ROD_META:
                return "item.rodIron";    
                
            case Reference.ITEM_IRON_FANBLADE_META:
                return "item.fanbladeIron";    
                
            case Reference.ITEM_FERRITE_MAGNET_META:
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
