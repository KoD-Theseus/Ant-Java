package Creatures.Ants.AntLabor;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private static int counter = 1; // Unique ID counter
    private static final List<Ant> allAnts = new ArrayList<>();

    private final String name;
    private float x, y;
    private int floor;

    private Task currentTask;

    // Ant statistics (could be expanded)
    private float speed = 0.6f; // Default movement speed

    // Constructor: spawn at a location
    public Ant(float x, float y, int floor) {
        this.name = "Ant#" + counter++;
        this.x = x;
        this.y = y;
        this.floor = floor;
        allAnts.add(this);
    }

    // --- Core Methods ---

    public void update(float deltaTime) {
        // Called every game tick
        if (currentTask != null) {
            moveTowardTask(deltaTime);
        } else {
            // Logic to find a new task would go here
        }
    }

    private void moveTowardTask(float deltaTime) {
        // Basic linear movement toward the task (replace with A* later)
        float dx = (float) (currentTask.getX() - x);
        float dy = (float) (currentTask.getY() - y);
        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (distance > 0.4f) { // Small threshold to "arrive"
            x += (float)((dx / distance) * speed * deltaTime);
            y += (float)((dy / distance) * speed * deltaTime);
        } else {
            arriveAtTask();
        }
        System.out.printf("%s moving toward task at (%.2f, %.2f) | Current position: (%.2f, %.2f)\n",
                name, currentTask.getX(), currentTask.getY(), x, y);

    }


    private void arriveAtTask() {
        // When ant arrives at task location
        System.out.println(name + " arrived at task at (" + currentTask.getX() + ", " + currentTask.getY() + ")");
        currentTask.complete(this);
        currentTask = null;
    }

    // --- Getters and Setters ---

    public static List<Ant> getAllAnts() {
        return allAnts;
    }

    public String getName() {
        return name;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return this.y;
    }

    public int getFloor() {
        return floor;
    }

    public void setTask(Task task) {
        this.currentTask = task;
    }

    public Task getTask() {
        return currentTask;
    }

    public void moveTo(float targetX, float targetY, int targetFloor) {
        // Set a manual movement target (could be pathfinding output)
        this.x = targetX;
        this.y = targetY;
        this.floor = targetFloor;
    }
    // Placeholder methods for PrioritizationMethod to use
    public String getSkill() {
        return "Foraging"; // default skill for now
    }

    public float getEfficiency() {
        return 1.0f; // default efficiency
    }

    public float getEnergy() {
        return 100.0f; // default energy
    }

}
