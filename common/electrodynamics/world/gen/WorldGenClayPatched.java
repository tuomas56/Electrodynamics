package electrodynamics.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import electrodynamics.lib.block.BlockIDs;

public class WorldGenClayPatched extends WorldGenerator
{
    private int clayBlockId;
    private int clayModBlockId;

    private int numberOfBlocks;

    public WorldGenClayPatched(int par1)
    {
        this.clayBlockId = Block.blockClay.blockID;
        this.clayModBlockId = BlockIDs.BLOCK_LITHIUM_CLAY_ID;
        this.numberOfBlocks = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
        {
            return false;
        }
        else
        {
            int l = par2Random.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;

            for (int i1 = par3 - l; i1 <= par3 + l; ++i1)
            {
                for (int j1 = par5 - l; j1 <= par5 + l; ++j1)
                {
                    int k1 = i1 - par3;
                    int l1 = j1 - par5;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = par4 - b0; i2 <= par4 + b0; ++i2)
                        {
                            int j2 = par1World.getBlockId(i1, i2, j1);

                            if (j2 == Block.dirt.blockID || j2 == Block.blockClay.blockID)
                            {
                            	//10% chance of clay block being lithium clay
                            	if(par2Random.nextInt(9) == 0)
                            		par1World.setBlock(i1, i2, j1, this.clayModBlockId, 0, 2);
                            	else
                            		par1World.setBlock(i1, i2, j1, this.clayBlockId, 0, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}

