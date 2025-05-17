package net.quepierts.baddestapple.client;

import com.mojang.blaze3d.shaders.AbstractUniform;
import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.vertex.VertexFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@RequiredArgsConstructor
public class ShaderHolder {
    private static final AbstractUniform DUMMY_UNIFORM = new AbstractUniform();

    @Getter
    private final ResourceLocation location;
    @Getter private final VertexFormat format;

    private ShaderInstance instance;
    private AbstractUniform style;

    public void setInstance(ShaderInstance instance) {
        this.style = DUMMY_UNIFORM;

        this.instance = instance;
        if (instance != null) {
            this.style = instance.safeGetUniform("Style");
        }
    }

    public void setStyle(boolean white) {
        style.set(white ? 1.0f : 0.0f);
    }

    public @NotNull ShaderInstance getInstance() {
        return Objects.requireNonNull(instance, "Attempted to call get shader [" + location + "] before shaders have finished loading.");
    }
}
