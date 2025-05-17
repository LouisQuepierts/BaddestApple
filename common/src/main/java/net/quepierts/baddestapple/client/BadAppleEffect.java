package net.quepierts.baddestapple.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ShaderInstance;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class BadAppleEffect {
    private static RenderTarget tmp;

    public static void init() {
        Window window = Minecraft.getInstance().getWindow();
        tmp = new TextureTarget(window.getWidth(), window.getHeight(), false, Minecraft.ON_OSX);
    }

    public static void apply() {
        float left = ClientSettings.getLeft();
        if (left <= 0) {
            return;
        }

        RenderTarget target = Minecraft.getInstance().getMainRenderTarget();

        int width = tmp.width;
        int height = tmp.height;

        target.bindRead();
        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, target.frameBufferId);
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, tmp.frameBufferId);
        GL30.glBlitFramebuffer(
                0, 0, width, height,
                0, 0, width, height,
                GL11.GL_COLOR_BUFFER_BIT, GL11.GL_NEAREST
        );

        target.bindWrite(true);

        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);

        RenderSystem.assertOnRenderThread();
        GlStateManager._colorMask(true, true, true, false);
        GlStateManager._disableDepthTest();
        GlStateManager._depthMask(false);
        GlStateManager._viewport(0, 0, width, height);

        ShaderInstance shaderinstance = Shaders.MASK_EFFECT.getInstance();
        shaderinstance.setSampler("Sampler0", tmp.getColorTextureId());
        shaderinstance.safeGetUniform("MaskLeft").set(left);
        shaderinstance.apply();
        BufferBuilder bufferbuilder = RenderSystem.renderThreadTesselator().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.addVertex(-1.0F, -1.0F, 0.0F).setUv(0.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, -1.0F, 0.0F).setUv(1.0F, 0.0F);
        bufferbuilder.addVertex(1.0F, 1.0F, 0.0F).setUv(1.0F, 1.0F);
        bufferbuilder.addVertex(-1.0F, 1.0F, 0.0F).setUv(0.0F, 1.0F);
        BufferUploader.draw(bufferbuilder.buildOrThrow());
        shaderinstance.clear();
        GlStateManager._depthMask(true);
        GlStateManager._colorMask(true, true, true, true);

        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    public static void resize(int width, int height) {
        if (tmp != null) {
            tmp.resize(width, height, true);
        }
    }

    public static void free() {
        if (tmp != null) {
            tmp.destroyBuffers();
            tmp = null;
        }
    }
}
