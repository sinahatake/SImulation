package actions.turnActions;

import actions.Action;
import entity.Entity;
import entity.Grass;
import entity.creature.Creature;
import entity.creature.Herbivore;
import entity.creature.Predator;
import map.WorldMap;

import java.util.Set;

public class MoveAllCreaturesAction implements Action {
    @Override
    public void execute(WorldMap map) {
        Set<Entity> allEntities = map.getAllEntities();

        for (Entity entity : allEntities) {

            if (entity instanceof Creature creature) {

                Class<? extends Entity> targetType = null;

                if (creature instanceof Predator) {
                    targetType = Herbivore.class;
                } else if (creature instanceof Herbivore) {
                    targetType = Grass.class;
                }

                if (targetType != null) {
                    try {
                        creature.makeMove(map, targetType);
                    } catch (IllegalStateException _) {

                    }
                }
            }
        }
    }
}