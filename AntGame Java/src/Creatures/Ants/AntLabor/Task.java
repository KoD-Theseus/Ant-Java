package Creatures.Ants.AntLabor;

public class Task {
    private String name;       // Unique identifier for the task
    private double x, y;       // Task's location coordinates
    private String requiredSkill; // The skill required to complete this task
    private int priority;      // Priority level (higher = more important)
    private boolean isCompleted; // Task state
    private int floor; // floor level where the task exists

    // Constructor
    public Task(String name, double x, double y, int floor, String requiredSkill, int priority) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.requiredSkill = requiredSkill;
        this.priority = priority;
        this.isCompleted = false; // Default to not completed
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getFloor() {
        return floor;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setters
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    // Updated complete method with Ant passed as parameter
    public void complete(Ant ant) {
        isCompleted = true;
        System.out.println("Task completed at (" + x + ", " + y + ") by " + ant.getName() + " on floor " + ant.getFloor());
    }
}
