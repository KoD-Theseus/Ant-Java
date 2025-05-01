package Creatures.Ants.AntLabor;

import java.util.*;
import Creatures.Ants.AntLabor.Ant;

public class AntTaskManager {
    // Represents the position of an Ant in the grid
    private final LaborPathfind pathfinder; // Instance of LaborPathfind for pathfinding operations
    private final Map<Ant, Map<Task, Float>> antTaskDistances; // Stores distances from each ant to each task

    public AntTaskManager() {
        this.pathfinder = new LaborPathfind();
        this.antTaskDistances = new HashMap<>();
    }

    /**
     * Calculates and stores the distance from each ant to all tasks.
     *
     * @param ants  List of ants with their positions
     * @param tasks List of tasks with their positions
     */
    public void calculateDistances(List<Ant> ants, List<Task> tasks) {
        for (Ant ant : ants) {
            Map<Task, Float> taskDistances = new HashMap<>();
            for (Task task : tasks) {
                float distance = pathfinder.calculateDistance(
                        ant.getX(), ant.getY(), ant.getFloor(), task.getX(), task.getY(), task.getFloor()
                );
                taskDistances.put(task, distance);
            }
            antTaskDistances.put(ant, taskDistances);
        }
    }

    /**
     * Retrieves the distances of a specific ant to all tasks.
     *
     * @param ant The ant whose distances to tasks are queried
     * @return A map of tasks with their corresponding distances
     */
    public Map<Task, Float> getTaskDistancesForAnt(Ant ant) {
        return antTaskDistances.getOrDefault(ant, Collections.emptyMap());
    }

    /**
     * Prints the distance mappings for each ant to all tasks.
     */
    public void printAntTaskDistances() {
        for (Map.Entry<Ant, Map<Task, Float>> entry : antTaskDistances.entrySet()) {
            Ant ant = entry.getKey();
            Map<Task, Float> taskDistances = entry.getValue();

            System.out.printf("Ant at (%d, %d, %d):\n", ant.getX(), ant.getY(), ant.getFloor());
            for (Map.Entry<Task, Float> taskEntry : taskDistances.entrySet()) {
                Task task = taskEntry.getKey();
                float distance = taskEntry.getValue();
                System.out.printf("  Task at (%d, %d, %d): Distance = %.2f\n", task.getX(), task.getY(), task.getFloor(), distance);
            }
        }
    }
}