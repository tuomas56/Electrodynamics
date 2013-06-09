package electrodynamics.world.handler;

import electrodynamics.world.gen.WorldGenPlant;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;

public class DecorateBiomeEventHandler {

	@ForgeSubscribe
	public void onDecorate(final Decorate event) {
		if (event.type == DecorateBiomeEvent.Decorate.EventType.GRASS || event.type == DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH) {
			WorldGenPlant.generate( event.world, event.chunkX, event.chunkZ, event.rand );
		}
	}

}
