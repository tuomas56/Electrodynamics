package electrodynamics.item;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import electrodynamics.core.CoreUtils;
import electrodynamics.core.CreativeTabED;
import electrodynamics.entity.EntityDolly;
import electrodynamics.lib.core.ModInfo;

public class ItemDolly extends Item {

	public static ArrayList<BlacklistedBlock> blockBlacklist = new ArrayList<BlacklistedBlock>();
	
	private Icon texture;
	
	public ItemDolly(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabED.tool);
		
		fillBlacklist();
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		this.texture = register.registerIcon(ModInfo.ICON_PREFIX + "tool/toolDolly");
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (getBlacklistData(id) != null) {
			BlacklistedBlock blacklisted = getBlacklistData(id);
			
			if (meta == blacklisted.meta || blacklisted.wildcard) {
				return false;
			}
		}
		
		EntityDolly dolly = new EntityDolly(world, x, y, z);
		world.spawnEntityInWorld(dolly);
		--stack.stackSize;
		world.setBlockToAir(x, y, z);
		if (CoreUtils.isClient(world)) {
			return false;
		} else {
			return true;	
		}
	}

	/* BLACKLIST STUFF */
	public void fillBlacklist() {
		addToBlacklist(Block.doorWood);
		addToBlacklist(Block.doorIron);
		addToBlacklist(Block.signPost);
		addToBlacklist(Block.signWall);
	}
	
	public void addToBlacklist(Block block) {
		this.addToBlacklist(block.blockID, OreDictionary.WILDCARD_VALUE);
	}
	
	public void addToBlacklist(int id, int meta) {
		blockBlacklist.add(new BlacklistedBlock(id, meta));
	}
	
	@SuppressWarnings("static-access")
	public BlacklistedBlock getBlacklistData(int id) {
		for (BlacklistedBlock blacklist : this.blockBlacklist) {
			if (blacklist.id == id) {
				return blacklist;
			}
		}
		
		return null;
	}
	
	public class BlacklistedBlock {
		public int id;
		public int meta;
		
		public boolean wildcard;
		
		public BlacklistedBlock(int id, int meta) {
			this.id = id;
			this.meta = meta;
			this.wildcard = meta == OreDictionary.WILDCARD_VALUE;
		}
	}
	
}
