package net.quepierts.baddestapple.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.quepierts.baddestapple.client.ClientSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    @Inject(
            method = "clearColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$clearColor(float red, float green, float blue, float alpha, CallbackInfo ci) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        float color = ClientSettings.isSkyWhite() ? 1.0f : 0.0f;
        GlStateManager._clearColor(color, color, color, 1);
        ci.cancel();
    }
}
