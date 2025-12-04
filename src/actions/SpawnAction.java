package actions;

import entity.Entity;
import map.WorldMap;

import java.util.function.Supplier;

public class SpawnAction implements Action {

    private final Supplier<? extends Entity> entitySupplier;
    private final int count;
    private final SpawnUtils spawnUtils = new SpawnUtils();

    public SpawnAction(Supplier<? extends Entity> entitySupplier, int count) {
        this.entitySupplier = entitySupplier;
        this.count = count;
    }

    @Override
    public void execute(WorldMap map) {
        for (int i = 0; i < count; i++) {
            map.addEntity(
                    spawnUtils.getValidRandomPosition(map),
                    entitySupplier.get()
            );
        }
    }
}
