package electrodynamics.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electrodynamics.core.CoreUtils;

public class EntityDolly extends Entity {

	public final int BLOCK_ID = 25;
	public final int BLOCK_META = 26;
	
	public int storedBlockID;
	public int storedBlockMeta;
	
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
	
	public int getBlockID() {
		return this.dataWatcher.getWatchableObjectInt(BLOCK_ID);
	}
	
	public int getBlockMeta() {
		return this.dataWatcher.getWatchableObjectInt(BLOCK_META);
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
	
	public void updateMotion() {
		if (CoreUtils.isServer(worldObj)) {
	        double d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	
	        if (d4 > 0.35D)
	        {
	            double d5 = 0.35D / d4;
	            this.motionX *= d5;
	            this.motionZ *= d5;
	            d4 = 0.35D;
	        }
	
	        if (this.onGround)
	        {
	            this.motionX *= 0.5D;
	            this.motionY *= 0.5D;
	            this.motionZ *= 0.5D;
	        }
	
	        this.moveEntity(this.motionX, this.motionY, this.motionZ);
		} else {
			double newX = this.posX + this.motionX;
			double newY = this.posY + this.motionY;
			double newZ = this.posZ + this.motionZ;
	        this.setPosition(newX, newY, newZ);
		}
	}
	
	public AxisAlignedBB getCollisionBox(Entity entity) {
		return entity.boundingBox;
	}

	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}
	
	public boolean canBePushed() {
		return true;
	}
	
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(BLOCK_ID, 0);
		this.dataWatcher.addObject(BLOCK_META, 0);
	}

	public void onUpdate() {
		super.onUpdate();
		
		updateBoundingBox();
		updateMotion();
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		setBlockID(nbt.getInteger("bid"));
		setBlockMeta(nbt.getInteger("bmeta"));
		
		if (nbt.hasKey("bdata")) {
			this.blockData = nbt.getCompoundTag("bdata");
		}
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("bid", getBlockID());
		nbt.setInteger("bmeta", getBlockMeta());
		
		if (this.blockData != null) {
			nbt.setCompoundTag("bdata", this.blockData);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
	
}
