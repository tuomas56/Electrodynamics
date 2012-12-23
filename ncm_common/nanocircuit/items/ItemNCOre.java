package nanocircuit.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nanocircuit.core.Reference;

public class ItemNCOre extends ItemBlock
{
    public ItemNCOre(int i)
    {
        super(i);
        setHasSubtypes(true);
        setItemName("ncOre");
    }

    public int getPlacedBlockMetadata(int i)
    {
        return i;
    }

    public int getMetadata(int i)
    {
        return i;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        switch (itemstack.getItemDamage())
        {
            case Reference.ORE_META.MAGNETITE:
                return "tile.oreMagnetite";

            case Reference.ORE_META.NICKEL:
                return "tile.oreNickel";
        }

        throw new IndexOutOfBoundsException();
    }
}
