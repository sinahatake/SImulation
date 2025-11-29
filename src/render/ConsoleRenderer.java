package render;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.Map;

public class ConsoleRenderer {

    public void printMap(WorldMap worldMap){
        int height = worldMap.height();
        int width = worldMap.width();
        String[][] grid = new String[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = "⬜";
            }
        }

        for (Map.Entry<Position, Entity> entry: worldMap.getOccupancy().entrySet()){
            Position pos = entry.getKey();
            Entity entity = entry.getValue();
            grid[pos.getX()][pos.getY()] = entity.getSymbol();
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }



    }
}
