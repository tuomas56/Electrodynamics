package electrodynamics.lib.block;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import electrodynamics.lib.core.ModInfo;
import electrodynamics.lib.core.Strings;
import electrodynamics.tileentity.machine.TileEntityBasicKiln;
import electrodynamics.tileentity.machine.TileEntityBasicSieve;
import electrodynamics.tileentity.machine.TileEntitySinteringOven;

public enum Machine {

	SINTERING_FURNACE(Strings.MACHINE_SINTERING_OVEN, "sinteringOven", TileEntitySinteringOven.class),
	BASIC_SIEVE(Strings.MACHINE_BASIC_SIEVE, "basicSieve", TileEntityBasicSieve.class),
	BASIC_KILN(Strings.MACHINE_BASIC_KILN, "basicKiln", TileEntityBasicKiln.class);	
	
	public String unlocalizedName;
	public String textureFileForParticle;
	
	public Class<? extends TileEntity> tileEntity;
	
	private Machine(String unlocalizedName, String particle, Class<? extends TileEntity> tileEntity) {
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
	
	public static Machine get(int ordinal) {
		return Machine.values()[ordinal];
	}
	
}
