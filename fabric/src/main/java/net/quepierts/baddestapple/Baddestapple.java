package net.quepierts.baddestapple;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.quepierts.baddestapple.client.ShaderHolder;
import net.quepierts.baddestapple.client.Shaders;

import java.io.IOException;
import java.util.Iterator;

public class Baddestapple implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CoreShaderRegistrationCallback.EVENT.register(this::onRegisterShaders);

        // TODO
    }

    private void onRegisterShaders(final CoreShaderRegistrationCallback.RegistrationContext context) throws IOException {
        for (Iterator<ShaderHolder> it = Shaders.shaders(); it.hasNext();) {
            ShaderHolder shader = it.next();
            context.register(shader.getLocation(), shader.getFormat(), shader::setInstance);
        }
    }
}
