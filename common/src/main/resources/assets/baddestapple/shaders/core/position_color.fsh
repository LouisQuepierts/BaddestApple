#version 150

in vec4 vertexColor;

uniform float Style;
uniform vec4 ColorModulator;

out vec4 fragColor;

void main() {
    float alpha = vertexColor.a;
    if (alpha == 0.0) {
        discard;
    }
    fragColor = vec4(Style, Style, Style, alpha);
}
