package map;

import entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final Map<Position, Entity> entities = new HashMap<>();

    public Map<Position, Entity> getEntities() {
        return new HashMap<>(entities);
    }

    public void addEntity(Position pos, Entity entity){
        entities.put(pos, entity);
        //сделать проверку на уникальность ключа
    }

    public void removeEntity(Position pos){
        entities.remove(pos);
    }

    public Entity getEntity(Position pos){
        return entities.get(pos);
    }

    public boolean containsEntity(Position pos){
        return entities.containsKey(pos);
    }


}
