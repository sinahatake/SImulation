package entity.creature;

import pathfinding.BFSPathFinder;
import pathfinding.PathFinder;

public class Herbivore extends Creature {
    private final PathFinder pathFinder = new BFSPathFinder();

    public Herbivore(int hp, int speed) {
        super(hp, speed);
    }

    @Override
    public String getSymbol() {
        return "\uD83D\uDC11";
    }


}

