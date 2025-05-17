package net.quepierts.baddestapple.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.quepierts.baddestapple.client.ClientSettings;
import net.quepierts.baddestapple.client.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(
            method = "getRendertypeSolidShader",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeSolidShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_SOLID.setStyle(ClientSettings.isTerrainWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_SOLID.getInstance());
    }

    @Inject(
            method = {"getRendertypeCutoutShader", "getRendertypeCutoutMippedShader"},
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeCutoutShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_CUTOUT.setStyle(ClientSettings.isTerrainWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_CUTOUT.getInstance());
    }

    @Inject(
            method = {"getRendertypeTranslucentShader", "getRendertypeTranslucentMovingBlockShader"},
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeTranslucentShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_TRANSLUCENT.setStyle(!ClientSettings.isTerrainWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_TRANSLUCENT.getInstance());
    }

    @Inject(
            method = "getRendertypeEntitySolidShader",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeEntitySolidShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_ENTITY_SOLID.setStyle(ClientSettings.isEntityWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_ENTITY_SOLID.getInstance());
    }

    @Inject(
            method = {"getRendertypeEntityCutoutShader", "getRendertypeEntityCutoutNoCullShader", "getRendertypeEntityCutoutNoCullZOffsetShader"},
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeEntityCutoutShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_ENTITY_CUTOUT.setStyle(ClientSettings.isEntityWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_ENTITY_CUTOUT.getInstance());
    }

    @Inject(
            method = {"getRendertypeEntityTranslucentShader", "getRendertypeEntityTranslucentCullShader"},
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeEntityTranslucentShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_ENTITY_TRANSLUCENT.setStyle(ClientSettings.isEntityWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_ENTITY_TRANSLUCENT.getInstance());
    }

    @Inject(
            method = "getRendertypeCloudsShader",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getRendertypeCloudsShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.RENDERTYPE_CLOUD.setStyle(!ClientSettings.isSkyWhite());
        cir.setReturnValue(Shaders.RENDERTYPE_CLOUD.getInstance());
    }

    @Inject(
            method = "getParticleShader",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void baddestapple$getParticleShader(CallbackInfoReturnable<ShaderInstance> cir) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }
        Shaders.PARTICLE.setStyle(ClientSettings.isEntityWhite());
        cir.setReturnValue(Shaders.PARTICLE.getInstance());
    }
}
