#version 150

uniform sampler2D Sampler0;

uniform float Style;
uniform vec4 ColorModulator;

in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 color = texture(Sampler0, texCoord0);
    float alpha = color.a;
    if (alpha == 0.0) {
        discard;
    }
    fragColor = vec4(alpha);
}
