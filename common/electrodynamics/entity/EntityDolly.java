package electrodynamics.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CoreUtils;
import electrodynamics.item.EDItems;

public class EntityDolly extends Entity {

	public final int BLOCK_ID = 25;
	public final int BLOCK_META = 26;
	public final int DOLLY_ROTATION = 27;
	
	public int storedBlockID;
	public int storedBlockMeta;
	
	public int dollyRotation;
	
	public NBTTagCompound blockData;
	
	public EntityDolly(World world) {
		super(world);
        this.preventEntitySpawning = true;
        this.setSize(1F, 0.6F);
	}

	public EntityDolly(World world, int x, int y, int z) {
		this(world);
		
		setPosition(x + .5, y, z + .5);
		setBlock(world, x, y, z);
	}
	
	public boolean interact(EntityPlayer player) {
		if (player.inventory.getCurrentItem() == null) {
			this.worldObj.setBlock((int)Math.floor(posX), (int)Math.floor(posY), (int)Math.floor(posZ), this.storedBlockID, this.storedBlockMeta, 3);
		
			if (this.blockData != null) {
				TileEntity tile = this.worldObj.getBlockTileEntity((int)Math.floor(posX), (int)Math.floor(posY), (int)Math.floor(posZ));
				
				tile.readFromNBT(this.blockData);
			}
			
			player.setCurrentItemOrArmor(0, new ItemStack(EDItems.itemDolly));
			this.setDead();
			
			return true;
		}
		
		return false;
	}
	
	public void setBlock(World world, int x, int y, int z) {
		setBlockID(world.getBlockId(x, y, z));
		setBlockMeta(world.getBlockMetadata(x, y, z));
		
		Block block = Block.blocksList[this.storedBlockID];
		
		if (block != null && block.hasTileEntity(this.storedBlockMeta)) {
			TileEntity tile = world.getBlockTileEntity(x, y, z);
			
			NBTTagCompound nbt = new NBTTagCompound();
			tile.writeToNBT(nbt);
			this.blockData = nbt;
		}
	}
	
	public void setBlock(int id, int meta) {
		setBlockID(id);
		setBlockMeta(meta);
	}
	
	public void setBlock(TileEntity tile) {
		setBlockID(tile.worldObj.getBlockId(tile.xCoord, tile.yCoord, tile.zCoord));
		setBlockMeta(tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord));
		
		NBTTagCompound nbt = new NBTTagCompound();
		tile.writeToNBT(nbt);
		this.blockData = nbt;
	}
	
	public void setBlockID(int id) {
		this.storedBlockID = id;
		this.dataWatcher.updateObject(BLOCK_ID, id);
	}
	
	public void setBlockMeta(int meta) {
		this.storedBlockMeta = meta;
		this.dataWatcher.updateObject(BLOCK_META, meta);
	}
	
	public void setRotation(int rotation) {
		if (rotation > 360) rotation = 0;
		if (rotation < 0) rotation = 360;
		
		this.dollyRotation = rotation;
		this.dataWatcher.updateObject(DOLLY_ROTATION, rotation);
	}
	
	public int getBlockID() {
		return this.dataWatcher.getWatchableObjectInt(BLOCK_ID);
	}
	
	public int getBlockMeta() {
		return this.dataWatcher.getWatchableObjectInt(BLOCK_META);
	}
	
	public int getRotation() {
		return this.dataWatcher.getWatchableObjectInt(DOLLY_ROTATION);
	}
	
	public boolean hasBlock() {
		return getBlockID() != 0;
	}
	
	/* ENTITY STUFF */
	public void updateBoundingBox() {
		if (getBlockID() != 0) {
			this.setSize(1F, 1F);
		} else {
			this.setSize(1F, 0.6F);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateMotion() {
		List<EntityPlayer> nearbyPlayers = worldObj.getEntitiesWithinAABB(EntityPlayer.class, getBoundingBox().expand(.25, 0, .25));
		
		if (nearbyPlayers != null && nearbyPlayers.size() > 0) {
//			setRotation(MathUtil.reverseNumber((int) -Math.floor(nearbyPlayers.get(0).rotationYaw), 1, 360));
			this.motionX *= nearbyPlayers.get(0).motionX;
			this.motionZ *= nearbyPlayers.get(0).motionZ;
		} else {
			this.motionX = 0;
			this.motionZ = 0;
		}
	}
	
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return entity.boundingBox;
	}

	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}
	
	@Override
	public boolean canBePushed() {
		return true;
	}
	
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(BLOCK_ID, new Integer(0));
		this.dataWatcher.addObject(BLOCK_META, new Integer(0));
		this.dataWatcher.addObject(DOLLY_ROTATION, new Integer(0));
	}

	public void onUpdate() {
		super.onUpdate();
		
		if (CoreUtils.isServer(worldObj)) {
			updateBoundingBox();
			updateMotion();
		}
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		setBlockID(nbt.getInteger("bid"));
		setBlockMeta(nbt.getInteger("bmeta"));
		
		this.dollyRotation = nbt.getInteger("rotation");
		
		if (nbt.hasKey("bdata")) {
			this.blockData = nbt.getCompoundTag("bdata");
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("bid", getBlockID());
		nbt.setInteger("bmeta", getBlockMeta());
		
		nbt.setInteger("rotation", this.dollyRotation);
		
		if (this.blockData != null) {
			nbt.setCompoundTag("bdata", this.blockData);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
	
}
