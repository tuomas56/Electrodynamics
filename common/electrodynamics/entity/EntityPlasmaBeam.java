package electrodynamics.entity;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityPlasmaBeam extends EntityBeam {

	public EntityPlasmaBeam(World world, double x, double y, double z, int age) {
		super(world, x, y, z, x + 1, y, z, age);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		
		this.posX += 0.2;
		this.tX += 0.2;
	}

	@Override
	public void onEntityIntersected(Entity entity) {
		entity.setFire(10);
	}
	
}
