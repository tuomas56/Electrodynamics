package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.machine.TileEntityBasicKiln;
import electrodynamics.tileentity.machine.TileEntityBasicSieve;
import electrodynamics.tileentity.machine.TileEntitySinteringOven;
import electrodynamics.tileentity.machine.energy.TileEntitySolarPanel;

public enum EnergyProduction {

	SOLAR_PANEL(Strings.ENERGY_SOLAR_PANEL, "solarPanel", TileEntitySolarPanel.class);
	
	public String unlocalizedName;
	public String textureFileForParticle;
	
	public Class<? extends TileEntity> tileEntity;
	
	private EnergyProduction(String unlocalizedName, String particle, Class<? extends TileEntity> tileEntity) {
		this.unlocalizedName = unlocalizedName;
		this.textureFileForParticle = particle;
		this.tileEntity = tileEntity;
	}
	
	public ItemStack toItemStack() {
		return new ItemStack(BlockIDs.BLOCK_MACHINE_ID, 1, this.ordinal());
	}
	
	public String getTextureFile() {
		return ModInfo.ICON_PREFIX + "machine/particle/" + textureFileForParticle;
	}
	
	public static EnergyProduction get(int ordinal) {
		return EnergyProduction.values()[ordinal];
	}
	
}
