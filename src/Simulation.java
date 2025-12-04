import actions.Action;
import actions.SpawnAction;
import actions.MoveAllCreaturesAction;
import entity.Grass;
import entity.Rock;
import entity.Tree;
import entity.creature.Creature;
import entity.creature.Herbivore;
import entity.creature.Predator;
import map.Position;
import map.WorldMap;
import render.ConsoleRenderer;

import java.util.Scanner;

public class Simulation {
    private final WorldMap map = new WorldMap(10, 10);
    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final Action moveAllCreaturesAction = new MoveAllCreaturesAction();
    private int step = 1;
    private boolean paused = false;

    public void startSimulation() {
        initSimulation();
        listenForCommands();
        while (true) {
            if (!paused) {
                nextTurn();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
            else {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {}
            }

        }
    }

    public void initSimulation() {
        System.out.println("=== СИМУЛЯЦИЯ ЗАПУЩЕНА ===");
        System.out.println("Нажмите 'P' для паузы, 'Q' для выхода");
        System.out.println("step " + step);
        initSpawnActions();
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
        if (map.numberOfEntityOfType(Grass.class) < 1) {
            new SpawnAction(Grass::new, 5).execute(map);
        }
        if (map.numberOfEntityOfType(Predator.class) < 1) {
            new SpawnAction(() -> new Predator(10, 1, 1), 5).execute(map);
        }
        if (map.numberOfEntityOfType(Herbivore.class) < 1) {
            new SpawnAction(() -> new Herbivore(10, 1), 5).execute(map);
        }

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

    private void listenForCommands() {
        Thread listener = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String cmd = scanner.nextLine().trim().toUpperCase();

                if (cmd.equals("P")) {
                    paused = !paused;
                    System.out.println(paused ? "=== SIMULATION PAUSED ===" : "=== SIMULATION RESUMED ===");
                }

                if (cmd.equals("Q")) {
                    System.out.println("=== SIMULATION STOPPED ===");
                    System.exit(0);
                }
            }
        });

        listener.setDaemon(true);
        listener.start();
    }


    private void initSpawnActions() {
        new SpawnAction(Grass::new, 5).execute(map);
        new SpawnAction(Tree::new, 5).execute(map);
        new SpawnAction(Rock::new, 5).execute(map);
        new SpawnAction(() -> new Predator(10, 1, 1), 5).execute(map);
        new SpawnAction(() -> new Herbivore(10, 1), 5).execute(map);
    }


}
