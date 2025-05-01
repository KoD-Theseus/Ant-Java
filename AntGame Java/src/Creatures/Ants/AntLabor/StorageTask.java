package Creatures.Ants.AntLabor;

import Structures.StorageBuilding;

public class StorageTask extends Task {
    private final StorageBuilding storage;
    private final String resourceType;
    private final int amount;

    public StorageTask(String name,
                       double x, double y, int floor,
                       String requiredSkill,
                       int priority,
                       StorageBuilding storage,
                       String resourceType,
                       int amount) {
        super(name, x, y, floor, requiredSkill, priority);
        this.storage = storage;
        this.resourceType = resourceType;
        this.amount = amount;
    }

    @Override
    public void complete(Ant ant) {
        super.complete(ant);
        int stored = storage.storeResource(resourceType, amount);
        System.out.println("Stored " + stored + " of " + resourceType
                + ". Free slots: " + storage.getFreeCapacity());
    }
}
