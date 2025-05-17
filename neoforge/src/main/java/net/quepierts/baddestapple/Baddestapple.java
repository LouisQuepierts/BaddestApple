package net.quepierts.baddestapple;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class BaddestApple {

    public BaddestApple(IEventBus eventBus) {
        Constants.LOG.info("Hello NeoForge world!");
    }
}
