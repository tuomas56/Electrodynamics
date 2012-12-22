package nanocircuit.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import nanocircuit.NanoCircuitMod;
import nanocircuit.core.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;

public class ItemPCB extends Item
{
	public ItemPCB(int i)
	{
		super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
        setCreativeTab(NanoCircuitMod.tabsNCM);
	}
	
	@Override
	public int getIconFromDamage(int meta)
	{
		return 8 + meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
		par3List.add(new ItemStack(par1, 1, 0));
    }
	
	@Override
    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.ITEM_BASIC_PCB_META:
                return "item.basicPCB";
        }

        throw new IndexOutOfBoundsException();
    }
	
	@Override
    public String getTextureFile()
    {
        return Reference.ITEM_TEXTURE;
    }

}
