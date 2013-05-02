package electrodynamics.core.item.tesla;

import java.util.HashSet;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet28EntityVelocity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import electrodynamics.codechicken.LightningBolt;
import electrodynamics.core.configuration.ConfigurationSettings;
import electrodynamics.core.control.IKeyBoundServer;
import electrodynamics.core.core.CreativeTabED;
import electrodynamics.core.item.ItemHandler;
import electrodynamics.core.lib.ModInfo;

public class ItemArmorTeslaChest extends ItemArmor implements IKeyBoundServer {

	private Icon texture;

	private HashSet<String> activeFor = new HashSet<String>();
	
	public ItemArmorTeslaChest(int id) {
		super(id, EnumArmorMaterial.IRON, 2, 1);
		setCreativeTab(CreativeTabED.item);
		setMaxStackSize(1);
		setMaxDamage(0);
	}

	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
		return stack.getItem() == ItemHandler.itemTeslaChest ? ModInfo.RESOURCES_BASE + "/armor/tesla_1.png" : null;
	}

	@SuppressWarnings({ "unchecked" })
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;
		if (!activeFor.contains(player.getEntityName())) return;
		
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

					PacketDispatcher.sendPacketToAllInDimension(new Packet28EntityVelocity(item), world.provider.dimensionId);

					LightningBolt bolt = new LightningBolt(world, player.posX, player.posY + 1, player.posZ, item.posX, item.posY, item.posZ, world.rand.nextLong(), 1);
					bolt.defaultFractal();
					bolt.setRandomType(4, 7);
					bolt.finalizeBolt();
				}
			}
		}
	}

	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.ICON_PREFIX + "tesla/chestplate");
	}

	@Override
	public void doKeybindingAction(EntityPlayer player, ItemStack stack, String key) {
		if (key.equals(ConfigurationSettings.MAGNET_TOGGLE_NAME)) {
			if (activeFor.contains(player.getEntityName())) {
				activeFor.remove(player.getEntityName());
				player.addChatMessage("Magnetic Attraction: " + EnumChatFormatting.RED + "OFF");
			} else {
				activeFor.add(player.getEntityName());
				player.addChatMessage("Magnetic Attraction: " + EnumChatFormatting.GREEN + "ON");
			}
		}
	}

}
