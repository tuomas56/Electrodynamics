package electrodynamics.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class EntityPlayerFake extends EntityPlayer {
	
	public static final String USERNAME = "[Electrodynamics]";
			
	public EntityPlayerFake(World par1World) {
		super(par1World, USERNAME);
		this.addedToChunk = true;
	}

	public void sendChatToPlayer(String msg) {
	}

	public boolean canCommandSenderUseCommand(int id, String msg) {
		return false;
	}

	public ChunkCoordinates getPlayerCoordinates() {
		return null;
	}

	public void setItemInHand(ItemStack stack) {
		this.inventory.currentItem = 0;
		this.inventory.setInventorySlotContents(0, stack);
	}

	public void openGui(Object mod, int id, World world, int x, int y, int z) {
	}

	public double getDistanceSq(double x, double y, double z) {
		return 0.0D;
	}

	public double getDistance(double x, double y, double z) {
		return 0.0D;
	}

	@Override
	public void func_110122_a(ChatMessageComponent chatmessagecomponent) {
		// Something something chat?
	}
}