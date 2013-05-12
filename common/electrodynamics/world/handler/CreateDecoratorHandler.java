package electrodynamics.world.handler;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent.CreateDecorator;

import electrodynamics.world.gen.WorldGenClayPatched;

public class CreateDecoratorHandler {
    @ForgeSubscribe
    public void onCreateDecorator(CreateDecorator event) {
    		event.newBiomeDecorator.clayGen = new WorldGenClayPatched(4);
    }
}
