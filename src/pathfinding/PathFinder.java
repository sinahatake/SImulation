package pathfinding;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.List;

public interface PathFinder {

    List<Position> findPath(Position start, Class<? extends Entity> targetType, WorldMap map);
}