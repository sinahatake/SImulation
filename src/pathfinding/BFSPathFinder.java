package pathfinding;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.*;

public class BFSPathFinder implements PathFinder {

    @Override
    public List<Position> findPath(Position start, Class<? extends Entity> targetType, WorldMap map) {
        Queue<Position> queue = new LinkedList<>();
        Map<Position, Position> parentMap = new HashMap<>();
        Set<Position> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            Entity currentEntity = map.getEntity(current);
            if (targetType.isInstance(currentEntity) && !current.equals(start)) {
                return reconstructPath(start, current, parentMap);
            }

            for (Position neighbor : getNeighbors(current, map)) {

                if (visited.contains(neighbor)) {
                    continue;
                }

                boolean isTarget = map.isEntityOfType(neighbor, targetType);
                boolean isPassable = map.isSquareEmpty(neighbor) || isTarget;

                if (isPassable) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);

                    if (isTarget) {
                        return reconstructPath(start, neighbor, parentMap);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private List<Position> getNeighbors(Position pos, WorldMap map) {
        List<Position> neighbors = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();

        Position[] potentialNeighbors = new Position[] {
                new Position(x, y - 1),
                new Position(x, y + 1),
                new Position(x - 1, y),
                new Position(x + 1, y)
        };

        for (Position neighbor : potentialNeighbors) {
            if (map.isInsideBounds(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    private List<Position> reconstructPath(Position start, Position target, Map<Position, Position> parentMap) {
        LinkedList<Position> path = new LinkedList<>();
        Position current = target;

        while (current != null && !current.equals(start)) {
            path.addFirst(current);
            current = parentMap.get(current);
        }

        return path;
    }
}

    

