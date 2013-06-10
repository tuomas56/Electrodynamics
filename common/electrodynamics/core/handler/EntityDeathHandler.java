package electrodynamics.core.handler;

import electrodynamics.core.misc.DamageSourceBlock;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntityDeathHandler {

	@ForgeSubscribe
	public void onPlayerDeath(LivingDropsEvent e) {
		// Prevent item drops if killed by mob grinder
		if (e.source instanceof DamageSourceBlock) {
			e.setCanceled(true);
		}
	}
	
}
