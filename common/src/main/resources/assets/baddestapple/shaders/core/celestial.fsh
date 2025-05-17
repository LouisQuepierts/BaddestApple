#version 150

uniform sampler2D Sampler0;

uniform float Style;
uniform vec4 ColorModulator;

in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    float alpha = max(max(color.r, color.g), color.b) / 1.0f;
    if (alpha == 0.0) {
        discard;
    }
    alpha = clamp(alpha + step(0.3943, alpha), 0, 1);
    fragColor = vec4(Style, Style, Style, alpha);
}
