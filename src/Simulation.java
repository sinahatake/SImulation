import actions.Action;
import actions.initActions.*;
import actions.turnActions.MoveAllCreaturesAction;
import entity.creature.Creature;
import map.Position;
import map.WorldMap;
import render.ConsoleRenderer;

public class Simulation {
    private final WorldMap map = new WorldMap(10, 10);
    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final Action moveAllCreaturesAction = new MoveAllCreaturesAction();
    private int step = 1;

    public void startSimulation() {
        initSimulation();
        while (!map.getAllCreatures().isEmpty()) {
            nextTurn();
        }
    }

    public void initSimulation() {
        System.out.println("step " + step);
        new SpawnGrassAction().execute(map);
        new SpawnRockAction().execute(map);
        new SpawnTreeAction().execute(map);
        new SpawnHerbivoreAction().execute(map);
        new SpawnPredatorAction().execute(map);
        logEntitiesState();
        renderer.printMap(map);
        System.out.println();
    }

    public void nextTurn() {
        step++;
        System.out.println("step " + step);
        moveAllCreaturesAction.execute(map);
        logEntitiesState();
        renderer.printMap(map);
        System.out.println();
    }

    public void logEntitiesState() {
        System.out.println("=== Entities state at step " + step + " ===");
        if (map.getAllCreatures().isEmpty()) {
            System.out.println("=== All the creatures died ===");
        } else {
            for (Creature creature : map.getAllCreatures()) {
                Position pos = map.getPositionByEntity(creature);
                String info = String.format(
                        "%s at (%d,%d) HP: %d, Satiety: %d",
                        creature.getClass().getSimpleName(),
                        pos.x(), pos.y(),
                        creature.getHp(),
                        creature.getSatiety()
                );
                System.out.println(info);
            }
            System.out.println("=====================================");
        }
    }

}
