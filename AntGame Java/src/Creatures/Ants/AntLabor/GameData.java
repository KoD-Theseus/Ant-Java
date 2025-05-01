package Creatures.Ants.AntLabor;

import java.util.ArrayList;
import java.util.List;
import Structures.StorageBuilding;
import Structures.SpriteManager;
import Structures.StorageBuilding;
import Structures.SpriteManager;

public class GameData {
    public static List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        ants.add(new Ant(0, 0, 0));
        ants.add(new Ant(5, 5, 0));
        ants.add(new Ant(10, 10, 0));
        return ants;
    }
    public static StorageBuilding createStorage(SpriteManager spriteManager) {
        // 100‐unit capacity example
        StorageBuilding storage = new StorageBuilding(
                SpriteManager.BuildingType.Storage,
                spriteManager,
                100
        );
        storage.initialize();
        return storage;
    }

    public static List<Task> createTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("ScoutArea", 16, 5, 0, "Foraging", 1));
        tasks.add(new Task("HarvestFood", 12, 7, 0, "Foraging", 2));
        return tasks;
    }
    public static StorageBuilding createStorage(SpriteManager spriteManager) {
        // 100‐unit capacity example
        StorageBuilding storage = new StorageBuilding(
                SpriteManager.BuildingType.Storage,
                spriteManager,
                100
        );
        storage.initialize();
        return storage;
    }

}
