#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
uniform float Style;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    float alpha = texture(Sampler0, texCoord0).a;
    if (alpha < 0.1) {
        discard;
    }

    vec4 color = vec4(Style, Style, Style, alpha);
    fragColor = color;
}
