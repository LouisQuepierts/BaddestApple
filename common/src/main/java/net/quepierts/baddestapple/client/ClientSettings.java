package net.quepierts.baddestapple.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public final class ClientSettings {
    @Getter @Setter private static boolean enableBadAppleStyle = false;

    @Getter @Setter private static boolean entityWhite = true;
    @Getter @Setter private static boolean terrainWhite = false;
    @Getter @Setter private static boolean skyWhite = false;

    @Getter private static float left = 0.0f;

    private static final Random RANDOM = new Random(42);

    public static void random() {
        entityWhite = RANDOM.nextBoolean();
        terrainWhite = RANDOM.nextBoolean();
        skyWhite = RANDOM.nextBoolean();

        if (entityWhite && terrainWhite && skyWhite) {
            entityWhite = false;
        } else if (!entityWhite && !terrainWhite && !skyWhite) {
            terrainWhite = true;
        }

        left = switch (RANDOM.nextInt(3)) {
            case 0 -> 0.0f;
            case 1 -> 0.5f;
            default -> 1.0f;
        };
    }
}
