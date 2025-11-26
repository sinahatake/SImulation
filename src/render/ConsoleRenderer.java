package render;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.Map;

public class ConsoleRenderer {
    private final int HIGTH = 10;
    private final int WIDTH = 10;
    private String[][] grid = new String[HIGTH][WIDTH];
    private final WorldMap worldMap = new WorldMap();

    public void printMap(){
        for (int i = 0; i < HIGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                grid[i][j] = "⬜";
            }
        }

        for (Map.Entry<Position, Entity> entry: worldMap.getEntities().entrySet()){
            Position pos = entry.getKey();
            Entity entity = entry.getValue();
            grid[pos.getX()][pos.getY()] = entity.getSymbol();
        }

        for (int i = 0; i < HIGTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }



    }
}
