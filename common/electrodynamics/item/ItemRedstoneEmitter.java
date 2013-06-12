package electrodynamics.item;

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import electrodynamics.core.CreativeTabED;
import electrodynamics.entity.EntityBeam;
import electrodynamics.lib.block.BlockIDs;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.tileentity.TileEntityRSSource;
import electrodynamics.util.PlayerUtil;

public class ItemRedstoneEmitter extends Item {

	public static final int MAX_POWER = 1600;
	
	private Icon[] textures;
	
	public static HashMap<String, EntityBeam> emissionBeams = new HashMap<String, EntityBeam>();
	public static HashMap<String, Integer> startCharges = new HashMap<String, Integer>();
	
	public ItemRedstoneEmitter(int id) {
		super(id);
		setMaxDamage(0);
		setMaxStackSize(1);
		setNoRepair();
		setCreativeTab(CreativeTabED.tool);
	}
	
	public static ItemStack getRemote(int chargeLevel) {
		ItemStack remote = new ItemStack(EDItems.itemRedstoneEmitter);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("charge", chargeLevel);
		remote.setTagCompound(nbt);
		return remote;
	}
	
	public static int getCharge(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return stack.getTagCompound().getInteger("charge");
	}
	
	public static void setCharge(ItemStack stack, int chargeLevel) {
		if (chargeLevel > MAX_POWER) chargeLevel = MAX_POWER;
		if (chargeLevel < 0) chargeLevel = 0;
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		NBTTagCompound nbt = stack.getTagCompound();
		nbt.setInteger("charge", chargeLevel);
		stack.setTagCompound(nbt);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		// Don't know what this actually does...
		return 72000;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		String id = "BEAM: " + player.username;
		
		if (getCharge(stack) > 0) {
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			
			startCharges.put(id, getCharge(stack));
		}
		
		return stack;
	}
	
	public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count) {
		String id = "BEAM: " + player.username;
		
		int charge = startCharges.get(id);
		
		final float RANGE = 35F;
		EntityBeam laser = null;
		
		World world = player.worldObj;
		
		if (charge > 0 && charge - count > 0) {
			double[] lookCoords = PlayerUtil.getLookCoordinates(player, RANGE);
			MovingObjectPosition lookedBlock = PlayerUtil.getLookedBlock(player.worldObj, player, world.getWorldVec3Pool().getVecFromPool(lookCoords[0], lookCoords[1], lookCoords[2]));
			
			if (lookedBlock != null) {
				ForgeDirection side = ForgeDirection.getOrientation(lookedBlock.sideHit);
				
				if (side != null) { // Can happen, doesn't hurt to check
					int x = lookedBlock.blockX + side.offsetX;
					int y = lookedBlock.blockY + side.offsetY;
					int z = lookedBlock.blockZ + side.offsetZ;
					
					if (world.getBlockId(lookedBlock.blockX, lookedBlock.blockY, lookedBlock.blockZ) != BlockIDs.BLOCK_REDSTONE_SOURCE_ID) {
						if (world.getBlockId(x, y, z) == 0) {
							world.setBlock(x, y, z, BlockIDs.BLOCK_REDSTONE_SOURCE_ID);
						} else {
							TileEntity tile = world.getBlockTileEntity(x, y, z);

							if (tile != null && tile instanceof TileEntityRSSource) {
								((TileEntityRSSource)tile).keepAlive();
							}
						}
					}
				}
				
				//TODO Modify beam start position to not be from the head... lol
				if (emissionBeams.get(id) == null) {
					laser = new EntityBeam(world, player.posX, player.posY + player.getEyeHeight(), player.posZ, lookedBlock.blockX + .5, lookedBlock.blockY + .5, lookedBlock.blockZ + .5, 1);
					laser.setRGB(0, 255, 0);
					laser.setEndMod(1.0F);
					laser.setPulse(false);
					
					world.spawnEntityInWorld(laser);
					
					emissionBeams.put(id, laser);
				} else {
					emissionBeams.get(id).updateBeam(player.posX, player.posY + player.getEyeHeight(), player.posZ, lookedBlock.blockX + .5, lookedBlock.blockY + .5, lookedBlock.blockZ + .5);
					emissionBeams.get(id).setRGB(0, 255, 0);
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
			player.stopUsingItem();
		}
    }
	
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
		String id = "BEAM: " + player.username;
		
		emissionBeams.put(id, null);
		
		// Discharge is done here to prevent constant item update animation while in use
		setCharge(stack, getCharge(stack) - count);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add("Power: " + getCharge(stack) + "/" + MAX_POWER);
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
	
	public int getItemDamageFromStack(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			return 0;
		}

		return getCharge(stack);
	}

	public int getItemDamageFromStackForDisplay(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			return 0;
		}

		return getCharge(stack);
	}

	public int getItemMaxDamageFromStack(ItemStack stack) {
		NBTTagCompound tags = stack.getTagCompound();
		if (tags == null) {
			return 0;
		}

		return MAX_POWER;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.textures = new Icon[2];
		
		this.textures[0] = register.registerIcon(ModInfo.ICON_PREFIX + "tool/redstoneEmitter");
		this.textures[1] = register.registerIcon(ModInfo.ICON_PREFIX + "tool/redstoneEmitterEmpty");
	}
	
}
