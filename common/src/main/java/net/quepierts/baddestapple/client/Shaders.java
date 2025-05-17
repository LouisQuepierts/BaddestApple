package net.quepierts.baddestapple.client;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.resources.ResourceLocation;
import net.quepierts.baddestapple.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shaders {
    public static final ShaderHolder RENDERTYPE_SOLID;

    public static final ShaderHolder RENDERTYPE_CUTOUT;

    public static final ShaderHolder RENDERTYPE_TRANSLUCENT;

    public static final ShaderHolder RENDERTYPE_ENTITY_SOLID;

    public static final ShaderHolder RENDERTYPE_ENTITY_CUTOUT;

    public static final ShaderHolder RENDERTYPE_ENTITY_TRANSLUCENT;

    public static final ShaderHolder RENDERTYPE_CLOUD;

    public static final ShaderHolder POSITION;

    public static final ShaderHolder POSITION_COLOR;

    public static final ShaderHolder POSITION_TEX;

    public static final ShaderHolder POSITION_TEX_COLOR;

    public static final ShaderHolder PARTICLE;

    public static final ShaderHolder CELESTIAL;

    public static final ShaderHolder MASK_EFFECT;

    private static final List<ShaderHolder> SHADERS;

    public static Iterator<ShaderHolder> shaders() {
        return SHADERS.iterator();
    }

    private static ShaderHolder shader(String name, VertexFormat format) {
        ShaderHolder shader = new ShaderHolder(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name), format);
        SHADERS.add(shader);
        return shader;
    }

    static {
        SHADERS = new ArrayList<>();

        RENDERTYPE_SOLID = shader("rendertype_solid", DefaultVertexFormat.BLOCK);
        RENDERTYPE_CUTOUT = shader("rendertype_cutout", DefaultVertexFormat.BLOCK);
        RENDERTYPE_TRANSLUCENT = shader("rendertype_translucent", DefaultVertexFormat.BLOCK);
        RENDERTYPE_ENTITY_SOLID = shader("rendertype_entity_solid", DefaultVertexFormat.NEW_ENTITY);
        RENDERTYPE_ENTITY_CUTOUT = shader("rendertype_entity_cutout", DefaultVertexFormat.NEW_ENTITY);
        RENDERTYPE_ENTITY_TRANSLUCENT = shader("rendertype_entity_translucent", DefaultVertexFormat.NEW_ENTITY);

        RENDERTYPE_CLOUD = shader("rendertype_clouds", DefaultVertexFormat.POSITION_TEX_COLOR_NORMAL);

        POSITION = shader("position", DefaultVertexFormat.POSITION);
        POSITION_COLOR = shader("position_color", DefaultVertexFormat.POSITION_COLOR);
        POSITION_TEX = shader("position_tex", DefaultVertexFormat.POSITION_TEX);
        POSITION_TEX_COLOR = shader("position_tex_color", DefaultVertexFormat.POSITION_TEX_COLOR);
        PARTICLE = shader("particle", DefaultVertexFormat.PARTICLE);
        CELESTIAL = shader("celestial", DefaultVertexFormat.POSITION_TEX);

        MASK_EFFECT = shader("mask_effect", DefaultVertexFormat.POSITION_TEX);
    }
}
