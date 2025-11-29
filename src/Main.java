import map.WorldMap;
import render.ConsoleRenderer;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(10, 10);

        ConsoleRenderer renderer = new ConsoleRenderer();
        renderer.printMap(map);


    }
}
