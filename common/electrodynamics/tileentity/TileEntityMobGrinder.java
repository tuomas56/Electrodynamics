package electrodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;

public class TileEntityMobGrinder extends TileStructure {

	@Override
	public void updateEntity() {
		System.out.println("MOB GRINDER GO!");
	}
	
	@Override
	public boolean onBlockActivatedBy(EntityPlayer player, int side, float xOff, float yOff, float zOff) {
		return false;
	}

}
