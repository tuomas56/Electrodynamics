package nanocircuit.world;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import nanocircuit.core.Reference;

public class ItemOre extends ItemBlock 
{
    public ItemOre(int i)
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
