package net.quepierts.baddestapple.mixin;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.quepierts.baddestapple.client.BadAppleEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow @Final private Window window;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void baddestapple$init(CallbackInfo info) {
        BadAppleEffect.init();
    }

    @Inject(at = @At("TAIL"), method = "resizeDisplay")
    private void baddestapple$resizeDisplay(CallbackInfo ci) {
        BadAppleEffect.resize(this.window.getWidth(), this.window.getHeight());
    }

    @Inject(at = @At("TAIL"), method = "stop")
    private void baddestapple$stop(CallbackInfo info) {
        BadAppleEffect.free();
    }
}
