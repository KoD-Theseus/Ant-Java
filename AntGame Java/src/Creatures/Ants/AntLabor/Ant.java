package Creatures.Ants.AntLabor;

import java.util.ArrayList;
import java.util.List;

public class Ant {
    private static int counter = 1; // For generating unique names
    private final String name;

    private static final List<Ant> allAnts = new ArrayList<>();

    // Constructor with a custom name
    public Ant(String name) {
        this.name = name;
        allAnts.add(this);
    }

    // Constructor with auto-generated name
    public Ant() {
        this.name = "Ant#" + counter++;
        allAnts.add(this);
    }

    public static List<Ant> getAllAnts() {
        return allAnts;
    }

    public static String getName() {
        return name;
    }

    public float getSkillEfficiencyForTask(String task) {
        return 0f; // Placeholder for future logic
    }
}