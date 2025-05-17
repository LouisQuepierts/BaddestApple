package net.quepierts.baddestapple;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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
