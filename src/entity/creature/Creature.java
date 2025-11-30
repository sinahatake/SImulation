package entity.creature;

import entity.Entity;
import map.Position;
import map.WorldMap;
import pathfinding.BFSPathFinder;
import pathfinding.PathFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Creature extends Entity {
    private final int speed;
    private int hp;
    private int satiety = 10;

    private final PathFinder pathFinder = new BFSPathFinder();

    public void makeMove(WorldMap map, Class<? extends Entity> targetType) {
        if (!applyHunger(map)) return;

        Position currentPos = map.getPositionByEntity(this);
        if (currentPos == null) return;

        List<Position> path = pathFinder.findPath(currentPos, targetType, map);

        if (path.isEmpty()) {
            makeRandomMove(map);
            return;
        }

        if (path.size() == 1) {
            map.removeEntity(path.getFirst());
            map.moveEntity(this, path.getFirst());
            satiety = 10;
            return;
        }

        int stepsToMove = Math.min(speed, path.size() - 1);
        Position finalMove = path.get(stepsToMove - 1);
        map.moveEntity(this, finalMove);
    }

    public boolean applyHunger(WorldMap map) {
        satiety--;

        if (satiety < 0) {
            hp--;
        }

        if (hp <= 0) {
            Position p = map.getPositionByEntity(this);
            if (p != null) map.removeEntity(p);
            return false;
        }

        return true;
    }

    private void makeRandomMove(WorldMap map) {
        Position pos = map.getPositionByEntity(this);
        if (pos == null) return;

        List<int[]> dirs = new ArrayList<>(Arrays.asList(
                new int[]{1, 0},
                new int[]{-1, 0},
                new int[]{0, 1},
                new int[]{0, -1}
        ));

        Collections.shuffle(dirs);

        for (int[] d : dirs) {
            Position np = new Position(pos.x() + d[0], pos.y() + d[1]);
            if (map.isInsideBounds(np) && map.isSquareEmpty(np)) {
                map.moveEntity(this, np);
                return;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public int getSatiety() {
        return satiety;
    }


    public Creature(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }



}
