package electrodynamics.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

	public void setBlock(World world, int x, int y, int z) {
		setBlockID(world.getBlockId(x, y, z));
		setBlockMeta(world.getBlockMetadata(x, y, z));
		
		if (Block.blocksList[this.storedBlockID].hasTileEntity(this.storedBlockMeta)) {
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
		return this.storedBlockID != 0;
	}
	
	/* ENTITY STUFF */
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
		
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
	
}
