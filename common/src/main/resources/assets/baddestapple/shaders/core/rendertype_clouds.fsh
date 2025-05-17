#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float Style;

in vec2 texCoord0;
in float vertexDistance;
in vec4 vertexColor;

out vec4 fragColor;

void main() {
    float alpha = texture(Sampler0, texCoord0).a;
    if (alpha < 0.1) {
        discard;
    }
    fragColor = vec4(Style, Style, Style, alpha);
}
