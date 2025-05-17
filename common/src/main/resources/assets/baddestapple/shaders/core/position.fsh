#version 150

uniform float Style;

out vec4 fragColor;

void main() {
    fragColor = vec4(Style, Style, Style, 1);
}
