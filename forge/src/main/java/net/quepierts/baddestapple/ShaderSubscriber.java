package net.quepierts.baddestapple;

import net.minecraft.client.renderer.ShaderInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.quepierts.baddestapple.client.ShaderHolder;
import net.quepierts.baddestapple.client.Shaders;

import java.io.IOException;
import java.util.Iterator;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = Constants.MOD_ID)
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
