package net.quepierts.baddestapple.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexBuffer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.quepierts.baddestapple.client.ClientSettings;
import net.quepierts.baddestapple.client.Shaders;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Inject(
            method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShader(Ljava/util/function/Supplier;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void baddestapple$renderLevel$setShader(CallbackInfo ci) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }

        Shaders.POSITION.setStyle(ClientSettings.isSkyWhite());
        RenderSystem.setShader(Shaders.POSITION::getInstance);
    }

    @Inject(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShader(Ljava/util/function/Supplier;)V",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    private void baddestapple$renderSunset(CallbackInfo ci) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }

        RenderSystem.setShader(Shaders.POSITION_COLOR::getInstance);
    }

    @Inject(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShader(Ljava/util/function/Supplier;)V",
                    shift = At.Shift.AFTER,
                    ordinal = 1
            )
    )
    private void baddestapple$renderCelestial(CallbackInfo ci) {
        if (!ClientSettings.isEnableBadAppleStyle()) {
            return;
        }

        // sun / moon
        Shaders.CELESTIAL.setStyle(!ClientSettings.isSkyWhite());
        RenderSystem.setShader(Shaders.CELESTIAL::getInstance);
    }

    @Redirect(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/VertexBuffer;drawWithShader(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/client/renderer/ShaderInstance;)V",
                    ordinal = 1
            )
    )
    private void baddestapple$renderStars(
            VertexBuffer instance,
            Matrix4f p_254480_,
            Matrix4f p_254555_,
            ShaderInstance p_253993_
    ) {
        ShaderInstance shader = ClientSettings.isEnableBadAppleStyle() ? Shaders.POSITION.getInstance() : p_253993_;
        Shaders.POSITION.setStyle(!ClientSettings.isSkyWhite());
        instance.drawWithShader(p_254480_, p_254555_, shader);
    }

    @Redirect(
            method = "renderSky",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/VertexBuffer;drawWithShader(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/client/renderer/ShaderInstance;)V",
                    ordinal = 2
            )
    )
    private void baddestapple$renderDark(
            VertexBuffer instance,
            Matrix4f p_254480_,
            Matrix4f p_254555_,
            ShaderInstance p_253993_
    ) {
        ShaderInstance shader = ClientSettings.isEnableBadAppleStyle() ? Shaders.POSITION.getInstance() : p_253993_;
        Shaders.POSITION.setStyle(!ClientSettings.isSkyWhite());
        instance.drawWithShader(p_254480_, p_254555_, shader);
    }
}
