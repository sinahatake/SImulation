package actions;

import map.Position;
import map.WorldMap;

import java.util.Random;

public class SpawnUtils {
    private static final Random RANDOM = new Random();
    private static final int MAX_ATTEMPTS = 1000;

    public Position getValidRandomPosition(WorldMap map) {
        int width = map.getWidth();
        int height = map.getHeight();

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            int x = RANDOM.nextInt(width);
            int y = RANDOM.nextInt(height);
            Position pos = new Position(x, y);

            if (map.isSquareEmpty(pos)) {
                return pos;
            }
        }
        System.err.println("WARN: Не удалось найти свободное место для спавна.");
        return null;
    }
}