package pathfinding;

import entity.Entity;
import map.Position;
import map.WorldMap;

import java.util.*;

public class BFSPathFinder implements PathFinder {

    @Override
    public List<Position> findPath(Position start, Class<? extends Entity> targetType, WorldMap map) {
        Queue<Position> queue = new LinkedList<>();
        Set<Position> visited = new HashSet<>();
        Map<Position, Position> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (!current.equals(start) && map.isEntityOfType(current, targetType)) {
                return reconstructPath(start, current, parent);
            }

            for (Position next : getNeighbors(current, map)) {

                if (visited.contains(next))
                    continue;

                boolean target = map.isEntityOfType(next, targetType);
                boolean empty = map.isSquareEmpty(next);

                if (!empty && !target)
                    continue;

                visited.add(next);
                parent.put(next, current);
                queue.add(next);
            }
        }

        return Collections.emptyList();
    }

    private List<Position> getNeighbors(Position pos, WorldMap map) {
        List<Position> list = new ArrayList<>();
        int x = pos.x();
        int y = pos.y();

        Position[] arr = new Position[]{
                new Position(x, y - 1),
                new Position(x, y + 1),
                new Position(x - 1, y),
                new Position(x + 1, y)
        };

        for (Position p : arr) {
            if (map.isInsideBounds(p))
                list.add(p);
        }

        return list;
    }

    private List<Position> reconstructPath(Position start, Position goal, Map<Position, Position> parent) {
        LinkedList<Position> path = new LinkedList<>();
        Position cur = goal;

        while (!cur.equals(start)) {
            path.addFirst(cur);
            cur = parent.get(cur);
            if (cur == null)
                return Collections.emptyList();
        }

        return path;
    }
}
