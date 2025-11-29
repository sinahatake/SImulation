package entity.creature;

import entity.Entity;
import map.Position;
import map.WorldMap;
import pathfinding.BFSPathFinder;
import pathfinding.PathFinder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    private final PathFinder pathFinder = new BFSPathFinder();
    private List<Position> currentPath = new LinkedList<>();
    private Position cachedTarget = null;

    public void makeMove(WorldMap map, Class<? extends Entity> targetType) {
        Position currentPos = map.getPositionByEntity(this);

        boolean pathRecalculationNeeded = currentPath.isEmpty() ||
                (cachedTarget != null && map.getEntity(cachedTarget) == null);

        if (pathRecalculationNeeded) {
            List<Position> newPath = pathFinder.findPath(currentPos, targetType, map);

            if (newPath.isEmpty()) {
                currentPath = Collections.emptyList();
                cachedTarget = null;
                return;
            }

            currentPath = newPath;
            cachedTarget = currentPath.getLast();
        }

        if (currentPath.size() == 1) {
            map.removeEntity(cachedTarget);
            currentPath.clear();
            cachedTarget = null;
            return;
        }

        int stepsToMove = Math.min(speed, currentPath.size() - 1);

        if (stepsToMove > 0) {
            Position finalMove = currentPath.get(stepsToMove - 1);

            map.moveEntity(this, finalMove);

            for (int i = 0; i < stepsToMove; i++) {
                currentPath.removeFirst();
            }

        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Creature(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;
    }




}
