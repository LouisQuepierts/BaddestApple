package net.quepierts.baddestapple.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.phys.Vec3;
import net.quepierts.baddestapple.client.ClientSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {
    @Inject(
            method = "getSkyColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void baddestApple$getSkyColor(
            Vec3 pPos,
            float pPartialTick,
            CallbackInfoReturnable<Vec3> cir
    ) {
        if (ClientSettings.isEnableBadAppleStyle()) {
            double c = ClientSettings.isSkyWhite() ? 1.0D : 0.0D;
            cir.setReturnValue(new Vec3(c, c, c));
        }
    }
}
