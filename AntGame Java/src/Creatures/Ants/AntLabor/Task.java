package Creatures.Ants.AntLabor;

public class Task {
    private String name;       // Unique identifier for the task
    private double x, y;       // Task's location coordinates
    private String requiredSkill; // The skill required to complete this task
    private int priority;      // Priority level (higher = more important)
    private boolean isCompleted; // Task state

    // Constructor
    public Task(String name, double x, double y, String requiredSkill, int priority) {
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
}