package map;

import entity.Entity;
import entity.creature.Creature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WorldMap {
    private final int width;
    private final int height;

    private final Map<Position, Entity> occupancy = new HashMap<>();
    private final Map<Entity, Position> locations = new HashMap<>();

    public WorldMap(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Размеры карты должны быть положительными.");
        }
        this.width = width;
        this.height = height;
    }


    public void addEntity(Position pos, Entity entity) {
        if (!isInsideBounds(pos)) {
            throw new IllegalArgumentException("Позиция " + pos + " находится за пределами карты.");
        }
        if (occupancy.containsKey(pos)) {
            throw new IllegalStateException("Клетка " + pos + " уже занята сущностью: " + occupancy.get(pos));
        }
        if (locations.containsKey(entity)) {
            throw new IllegalStateException("Сущность " + entity + " уже находится на карте.");
        }

        occupancy.put(pos, entity);
        locations.put(entity, pos);
    }

    public void removeEntity(Position pos) {
        Entity entityToRemove = occupancy.get(pos);
        if (entityToRemove != null) {
            occupancy.remove(pos);
            locations.remove(entityToRemove);
        }
    }

    public void moveEntity(Entity entity, Position newPos) {
        if (!isInsideBounds(newPos)) {
            throw new IllegalArgumentException("Новая позиция " + newPos + " находится за пределами карты.");
        }

        Position oldPos = locations.get(entity);

        if (oldPos == null) {
            throw new IllegalStateException("Сущность " + entity + " не найдена на карте.");
        }
        if (occupancy.containsKey(newPos)) {
            throw new IllegalStateException("Клетка " + newPos + " уже занята.");
        }

        occupancy.remove(oldPos);
        occupancy.put(newPos, entity);
        locations.put(entity, newPos);
    }


    public Position getPositionByEntity(Entity entity) {
        return locations.get(entity);
    }

    public Set<Entity> getAllEntities() {
        return new HashSet<>(occupancy.values());
    }

    public Set<Creature> getAllCreatures() {
        Set<Creature> creatures = new HashSet<>();
        for (Entity entity : occupancy.values()) {
            if (entity instanceof Creature) {
                creatures.add((Creature) entity);
            }
        }
        return creatures;
    }

    public Map<Position, Entity> getOccupancy() {
        return new HashMap<>(occupancy);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInsideBounds(Position pos) {
        return pos.x() >= 0 && pos.x() < width && pos.y() >= 0 && pos.y() < height;
    }


    public boolean isSquareEmpty(Position pos) {
        if (!isInsideBounds(pos)) return false;
        return !occupancy.containsKey(pos);
    }


    public boolean isEntityOfType(Position pos, Class<? extends Entity> targetType) {
        Entity entity = occupancy.get(pos);
        if (entity == null) {
            return false;
        }
        return targetType.isInstance(entity);
    }
}