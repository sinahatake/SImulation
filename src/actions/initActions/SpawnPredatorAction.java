package actions.initActions;

import actions.Action;
import actions.SpawnUtils;
import entity.creature.Predator;
import map.WorldMap;

public class SpawnPredatorAction implements Action {
    private static final int COUNT = 5;
    private final SpawnUtils spawnUtils = new SpawnUtils();

    @Override
    public void execute(WorldMap map) {
        for (int i = 0; i < COUNT; i++) {
            map.addEntity(spawnUtils.getValidRandomPosition(map), new Predator(10, 1, 10));
        }

    }
}
