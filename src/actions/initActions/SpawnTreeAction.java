package actions.initActions;

import actions.Action;
import actions.SpawnUtils;
import entity.Tree;
import map.WorldMap;

public class SpawnTreeAction implements Action {
    private static final int COUNT = 5;
    private final SpawnUtils spawnUtils = new SpawnUtils();

    @Override
    public void execute(WorldMap map) {
        for (int i = 0; i < COUNT; i++) {
            map.addEntity(spawnUtils.getValidRandomPosition(map), new Tree());
        }

    }
}
