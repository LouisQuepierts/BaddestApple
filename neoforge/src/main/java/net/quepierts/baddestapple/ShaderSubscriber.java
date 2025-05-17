package net.quepierts.baddestapple;

import net.minecraft.client.renderer.ShaderInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import net.quepierts.baddestapple.client.ShaderHolder;
import net.quepierts.baddestapple.client.Shaders;

import java.io.IOException;
import java.util.Iterator;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = Constants.MOD_ID)
public class ShaderSubscriber {
    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        for (Iterator<ShaderHolder> it = Shaders.shaders(); it.hasNext(); ) {
            ShaderHolder shader = it.next();
            event.registerShader(
                    new ShaderInstance(
                            event.getResourceProvider(),
                            shader.getLocation(),
                            shader.getFormat()
                    ),
                    shader::setInstance
            );
        }
    }
}
