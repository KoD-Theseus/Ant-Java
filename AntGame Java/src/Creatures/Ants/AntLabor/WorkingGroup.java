package Creatures.Ants.AntLabor;

import java.util.*;

public class WorkingGroup {
    private List<Ant> idleAnts; // List of idle ants to manage
    private Map<String, Map<String, Object>> tasks; // Nested key-value map to store tasks (taskType -> taskDetails)
    private PrioritizationMethod prioritizationMethod; // Strategy for task prioritization

    public WorkingGroup() {
        this.idleAnts = new ArrayList<>();
        this.tasks = new HashMap<>();
        this.prioritizationMethod = PrioritizationMethod.DEFAULT;
    }

    // Assigns idle ants to tasks based on skills, priorities, or randomization
    public void assignTasks() {
        Iterator<Ant> antIterator = idleAnts.iterator();
        while (antIterator.hasNext()) {
            Ant ant = antIterator.next();
            String bestTask = findBestTaskForAnt(ant);

            if (bestTask != null) {
                assignTaskToAnt(ant, bestTask);
                antIterator.remove(); // Remove the ant from idle list once assigned
            }
        }
    }

    // Assigns a task to a specific ant
    private void assignTaskToAnt(Ant ant, String task) {
        System.out.println("Assigning task " + task + " to ant " + ant.getName());
        // Logic to actually assign the task to the ant would go here
    }

    // Finds the best task for an ant using skills, prioritization, and randomization
    private String findBestTaskForAnt(Ant ant) {
        if (tasks.isEmpty()) return null;

        List<String> availableTasks = new ArrayList<>(tasks.keySet());
        if (prioritizationMethod == PrioritizationMethod.SKILL_BASED) {
            return availableTasks.stream()
                    .max(Comparator.comparing(task -> calculateTaskEfficiency(ant, task))) // Skill-based efficiency
                    .orElse(null);
        } else if (prioritizationMethod == PrioritizationMethod.RANDOM) {
            Collections.shuffle(availableTasks);
            return availableTasks.get(0); // Pick a random task
        }

        // Default to first available task
        return availableTasks.get(0);
    }

    // Calculates the efficiency of an ant for a given task using skills and stats
    private float calculateTaskEfficiency(Ant ant, String task) {
        // This can consider stats like knowledge, experience, strengths/weaknesses, etc.
        return ant.getSkillEfficiencyForTask(task);
    }

    // Adds a task to the working group
    public void createTask(String taskType, Map<String, Object> taskDetails) {
        tasks.put(taskType, taskDetails);
    }

    // Searches for tasks if no tasks are available OR ants have low efficiency in current tasks
    public void searchForTasks() {
        if (!tasks.isEmpty()) return;
        System.out.println("Searching for additional tasks...");
        // Task discovery logic or integration with supply/resource system goes here
    }

    // Creates tasks based on available supplies (example implementation)
    public void createTasksBasedOnSupplies(Map<String, Integer> availableSupplies) {
        if (availableSupplies.getOrDefault("Food", 0) < 10) {
            createTask("HarvestFood", Map.of("priority", "high", "quantity", 15));
        }
        if (availableSupplies.getOrDefault("Wood", 0) < 5) {
            createTask("CollectWood", Map.of("priority", "medium", "quantity", 10));
        }
    }
}

//