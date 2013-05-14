package electrodynamics.item.tesla.logic;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.api.tool.ITeslaLogic;
import electrodynamics.configuration.ConfigurationSettings;
import electrodynamics.network.PacketTypeHandler;
import electrodynamics.network.packet.PacketLightningFX;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet28EntityVelocity;
import net.minecraft.world.World;

public class TeslaLogicMagneticPull implements ITeslaLogic {

	public int cooldown = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;
		
		List<EntityItem> nearbyItems = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(ConfigurationSettings.MAGNETIC_RANGE, ConfigurationSettings.MAGNETIC_RANGE, ConfigurationSettings.MAGNETIC_RANGE));

		for (EntityItem item : nearbyItems) {
			if (item != null) {
				if (item.delayBeforeCanPickup > 0) {
					return;
				}

				double d0 = 8.0D;
				double d1 = (player.posX - item.posX) / d0;
				double d2 = (player.posY + (double) player.getEyeHeight() - item.posY) / d0;
				double d3 = (player.posZ - item.posZ) / d0;
				double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
				double d5 = 1.0D - d4;

				if (d5 > 0.0D) {
					d5 *= d5;
					item.motionX += d1 / d4 * d5 * ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED;
					item.motionY += d2 / d4 * d5 * ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED;
					item.motionZ += d3 / d4 * d5 * ConfigurationSettings.MAGNETIC_ATTRACTION_SPEED;

					if (cooldown == 0) {
						PacketDispatcher.sendPacketToAllInDimension(new Packet28EntityVelocity(item), world.provider.dimensionId);
						PacketDispatcher.sendPacketToAllAround(player.posX, player.posY, player.posZ, 50D, player.worldObj.provider.dimensionId, PacketTypeHandler.fillPacket(new PacketLightningFX(player.posX, player.posY + 1.5, player.posZ, item.posX, item.posY, item.posZ, 1)));
						cooldown = 20;
					} else {
						cooldown++;
					}
				}
			}
		}
	}

}
