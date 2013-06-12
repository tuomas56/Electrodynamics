package electrodynamics.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.entity.EntityBeam;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.tileentity.TileEntityRSSource;
import electrodynamics.util.GLColor;
import electrodynamics.util.PlayerUtil;

public class ItemRedstoneEmitter extends ItemLaserTool {

	public ItemRedstoneEmitter(int id) {
		super(id);
	}

	private Icon[] textures;
	
	public static ItemStack getRemote(int chargeLevel) {
		ItemStack remote = new ItemStack(EDItems.itemRedstoneEmitter);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("charge", chargeLevel);
		remote.setTagCompound(nbt);
		return remote;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		list.add(ItemRedstoneEmitter.getRemote(0));
		list.add(ItemRedstoneEmitter.getRemote(MAX_POWER));
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		//TODO
//		if (getCharge(stack) > 0) {
//			return textures[0];
//		}
//		
//		return textures[1];
	
		return textures[0];
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.textures = new Icon[2];
		
		this.textures[0] = register.registerIcon(ModInfo.ICON_PREFIX + "tool/redstoneEmitter");
		this.textures[1] = register.registerIcon(ModInfo.ICON_PREFIX + "tool/redstoneEmitterEmpty");
	}

	@Override
	public boolean onTick(ItemStack stack, EntityPlayer player, int charge, int use) {
		String id = "BEAM: " + player.username;
		
		final float RANGE = 35F;
		EntityBeam laser = null;
		
		World world = player.worldObj;
		
		if (charge > 0 && charge - use > 0) {
			double[] lookCoords = PlayerUtil.getLookCoordinates(player, RANGE);
			MovingObjectPosition lookedBlock = PlayerUtil.getLookedBlock(player.worldObj, player, world.getWorldVec3Pool().getVecFromPool(lookCoords[0], lookCoords[1], lookCoords[2]));
			
			if (lookedBlock != null) {
				ForgeDirection side = ForgeDirection.getOrientation(lookedBlock.sideHit);
				GLColor laserColor = null;
				
				if (side != null) { // Can happen, doesn't hurt to check
					int x = lookedBlock.blockX + side.offsetX;
					int y = lookedBlock.blockY + side.offsetY;
					int z = lookedBlock.blockZ + side.offsetZ;
					
					if (world.getBlockId(lookedBlock.blockX, lookedBlock.blockY, lookedBlock.blockZ) != BlockIDs.BLOCK_REDSTONE_SOURCE_ID) {
						if (world.getBlockId(x, y, z) == 0) {
							world.setBlock(x, y, z, BlockIDs.BLOCK_REDSTONE_SOURCE_ID);
							
							laserColor = new GLColor(0, 255, 0);
						} else if (world.getBlockId(x, y, z) == BlockIDs.BLOCK_REDSTONE_SOURCE_ID) {
							TileEntity tile = world.getBlockTileEntity(x, y, z);

							if (tile != null && tile instanceof TileEntityRSSource) {
								((TileEntityRSSource)tile).keepAlive();
								
								laserColor = new GLColor(0, 255, 0);
							}
						} else {
							laserColor = new GLColor(255, 0, 0);
						}
					}
				}
				
				//TODO Modify beam start position to not be from the head, and cleanup this mess
				if (emissionBeams.get(id) == null) {
					laser = new EntityBeam(world, player.posX, player.posY + player.getEyeHeight(), player.posZ, lookedBlock.blockX + .5, lookedBlock.blockY + .5, lookedBlock.blockZ + .5, 1);
					laser.setRGB(laserColor.r, laserColor.g, laserColor.b);
					laser.setEndMod(1.0F);
					laser.setPulse(false);
					
					world.spawnEntityInWorld(laser);
					
					emissionBeams.put(id, laser);
				} else {
					emissionBeams.get(id).updateBeam(player.posX, player.posY + player.getEyeHeight(), player.posZ, lookedBlock.blockX + .5, lookedBlock.blockY + .5, lookedBlock.blockZ + .5);
					emissionBeams.get(id).setRGB(laserColor.r, laserColor.g, laserColor.b);
				}
			} else {
				if (emissionBeams.get(id) == null) {
					laser = new EntityBeam(world, player.posX, player.posY + player.getEyeHeight(), player.posZ, lookCoords[0], lookCoords[1], lookCoords[2], 1);
					laser.setRGB(255, 0, 0);
					laser.setEndMod(1.0F);
					laser.setPulse(false);
					
					world.spawnEntityInWorld(laser);
					
					emissionBeams.put(id, laser);
				} else {
					emissionBeams.get(id).updateBeam(player.posX, player.posY + player.getEyeHeight(), player.posZ, lookCoords[0], lookCoords[1], lookCoords[2]);
					emissionBeams.get(id).setRGB(255, 0, 0);
				}
			}
		} else {
			return true;
		}
		
		return false;
	}
	
}
