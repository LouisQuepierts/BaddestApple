#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float Style;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    float alpha = texture(Sampler0, texCoord0).a;
    if (alpha< 0.1) {
        discard;
    }

    fragColor = linear_fog(vec4(Style, Style, Style, 1), vertexDistance, FogStart, FogEnd, FogColor);
}
