package Creatures.Ants.AntLabor;

import java.util.List;

public class GameTest {
    public static void main(String[] args) {
        // Create some ants
// instead of manually new ArrayList and add(...)
        List<Ant> ants  = GameData.createAnts();
        List<Task> tasks = GameData.createTasks();

        // Assign one task to each ant using prioritization
        for (Task task : tasks) {
            Ant chosenAnt = PrioritizationMethod.DEFAULT.assignTask(task, ants);
            if (chosenAnt != null) {
                chosenAnt.setTask(task);
                System.out.println(chosenAnt.getName() + " assigned to task: " + task.getName());
            } else {
                System.out.println("No ant available for task: " + task.getName());
            }
        }

        // Simulate update loop (10 ticks)
        float deltaTime = 1.0f; // 1 second per tick
        for (int tick = 1; tick <= 90; tick++) {
            System.out.println("Tick " + tick);

            for (Ant ant : ants) {
                ant.update(deltaTime);
            }

            if (tick % 5 == 0) {
                System.out.println("-- Ant Positions at Tick " + tick + " --");
                for (Ant ant : ants) {
                    System.out.printf("%s: (%.2f, %.2f) on Floor %d\n", ant.getName(), ant.getX(), ant.getY(), ant.getFloor());
                }
            }

            System.out.println();
        }
    }
}
