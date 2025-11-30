package render;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.Map;

public class ConsoleRenderer {

    public void printMap(WorldMap worldMap){
        int height = worldMap.getHeight();
        int width = worldMap.getWidth();
        String[][] grid = new String[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = "⬜";
            }
        }

        for (Map.Entry<Position, Entity> entry: worldMap.getOccupancy().entrySet()){
            Position pos = entry.getKey();
            Entity entity = entry.getValue();
            grid[pos.y()][pos.x()] = entity.getSymbol();
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}