#version 150

uniform sampler2D Sampler0;
uniform float MaskLeft;

in vec2 texCoord0;

out vec4 fragColor;

void main() {
    float right = step(MaskLeft, texCoord0.x);
    float color = abs(right - texture(Sampler0, texCoord0).r);
    fragColor = vec4(color, color, color, 1);
}