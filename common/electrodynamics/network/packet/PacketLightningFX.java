package electrodynamics.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.Electrodynamics;
import electrodynamics.network.PacketTypeHandler;

public class PacketLightningFX extends PacketED {

	private double x1;
	private double y1;
	private double z1;
	private double x2;
	private double y2;
	private double z2;

	private int duration;
	
	public PacketLightningFX() {
		super(PacketTypeHandler.LIGHTNING, false);
	}
	
	public PacketLightningFX(double x1, double y1, double z1, double x2, double y2, double z2, int duration) {
		super(PacketTypeHandler.LIGHTNING, false);
		
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.duration = duration;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		this.x1 = data.readDouble();
		this.y1 = data.readDouble();
		this.z1 = data.readDouble();
		this.x2 = data.readDouble();
		this.y2 = data.readDouble();
		this.z2 = data.readDouble();
		this.duration = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeDouble(x1);
		dos.writeDouble(y1);
		dos.writeDouble(z1);
		dos.writeDouble(x2);
		dos.writeDouble(y2);
		dos.writeDouble(z2);
		dos.writeInt(duration);
	}

	@Override
	public void execute(INetworkManager network, Player player, Side side) {
		World world = ((EntityPlayer)player).worldObj;
		Electrodynamics.proxy.addLightningFX(world, x1, y1, z1, x2, y2, z2, world.rand.nextLong(), duration);
	}
	
}
