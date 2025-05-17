package net.quepierts.baddestapple.client;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public final class ClientSettings {
    @Getter @Setter private static boolean enableBadAppleStyle = false;

    @Getter @Setter private static boolean entityWhite = true;
    @Getter @Setter private static boolean terrainWhite = false;
    @Getter @Setter private static boolean skyWhite = false;

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
    }
}
