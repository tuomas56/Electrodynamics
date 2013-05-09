package electrodynamics.tileentity;

import cpw.mods.fml.common.network.PacketDispatcher;
import electrodynamics.interfaces.IHandleActivationPacket;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketActivate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class TileEntitySinteringOven extends TileEntityMachine implements IHandleActivationPacket {

	public final int ROTATIONAL_MAX = 3;
	
	public float doorAngle;
	
	public boolean open;
	
	public TileEntitySinteringOven() {
		doorAngle = 0;
		
		open = false;
	}
	
	@Override
	public void updateEntity() {
		if (open && doorAngle <= ROTATIONAL_MAX) {
			doorAngle += 0.2F;
		} else if (!open && doorAngle > 0) {
			doorAngle -= 0.2F;
		}
		
		if (doorAngle < 0) {
			doorAngle = 0;
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hX, float hY, float hZ) {
		open = !open;
		
		PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.fillPacket(new PacketActivate(x, y, z)), world.provider.dimensionId);
		
		return false;
	}

	@Override
	public void activate() {
		open = !open;
	}

}
