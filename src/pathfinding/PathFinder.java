package pathfinding;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.List;
import java.util.function.Predicate;

public interface PathFinder {
    List<Position> findPath(Position start, Position goal, WorldMap map);
    Position findNearest(Position start, Predicate<Entity> condition, WorldMap map);
}