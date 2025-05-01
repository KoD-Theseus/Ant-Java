package Structures;

import java.util.*;
import Creatures.Ants.AntLabor.Ant; // Assuming Ant represents worker ants

public class Building {

    // Enum for Building Types
    public enum BuildingType {
        Tunnel,
        Wall,
        Farm,
        QueensChambers,
        Storage
    }

    // Building Configuration
    private BuildingType type;
    private int maxCapacity = 10;           // Maximum capacity for tasks/resources
    private int currentCapacity = 0;       // Current usage
    private float constructionTime = 60f;  // Time in seconds to construct
    private int upgradeLevel = 0;          // Current upgrade level

    private boolean isUnderConstruction = true;
    private float progress = 0f;           // Construction progress
    private int workersAssigned = 0;       // Number of workers currently assigned

    // Dynamic Behavior
    private boolean dynamicGrowth = false; // For tunnels, farms, etc.
    private float growthRate = 0.05f;      // Growth rate per second
    private float width = 1f;              // For tunnels or building size

    // Resource Management
    private Map<String, Integer> requiredResources = new HashMap<>(); // Key: Resource Name, Value: Quantity

    // Reference to SpriteManager for sprite management
    private SpriteManager spriteManager;

    public Building(BuildingType type, SpriteManager spriteManager) {
        this.type = type;
        this.spriteManager = spriteManager;
    }

    // Initializes a building, setting its initial state
    public void initialize() {
        if (isUnderConstruction) {
            spriteManager.setSprite(this, 0); // Under construction
        } else {
            spriteManager.setSprite(this, upgradeLevel); // Completed or upgraded state
        }
    }

    // Main update logic (to mimic Unity's Update method)
    public void update(float deltaTime, List<Ant> ants) {
        if (isUnderConstruction) {
            constructBuilding(deltaTime);
        }

        if (dynamicGrowth) {
            int currentAnts = getNumberOfAntsInTunnel(ants);
            growBuilding(currentAnts, deltaTime);
        }
    }

    // Handles the construction logic
    private void constructBuilding(float deltaTime) {
        if (workersAssigned <= 0) return;

        float constructionRate = workersAssigned / constructionTime * deltaTime;
        progress += constructionRate;

        if (progress >= constructionTime) {
            isUnderConstruction = false;
            progress = constructionTime;
            spriteManager.setSprite(this, 1); // Construction complete sprite
            System.out.println(type + " completed!");
        }
    }

    // Calculate the number of ants in the "tunnel" building
    private int getNumberOfAntsInTunnel(List<Ant> ants) {
        int antCount = 0;

        for (Ant ant : ants) {
            // Check if the ant is within the bounds of the tunnel
            if (isAntInTunnel(ant.getX(), ant.getY())) {
                antCount++;
            }
        }

        return antCount;
    }

    // Determines if the ant is within the bounds of the tunnel
    private boolean isAntInTunnel(float antX, float antY) {
        // Define the tunnel's bounds
        float tunnelX = 0; // Replace with actual 'Building' position X
        float tunnelY = 0; // Replace with actual 'Building' position Y

        float tunnelWidth = width;
        return antX >= tunnelX - tunnelWidth / 2 &&
                antX <= tunnelX + tunnelWidth / 2 &&
                antY >= tunnelY - tunnelWidth / 2 &&
                antY <= tunnelY + tunnelWidth / 2;
    }

    // Handles dynamic growth logic for tunnels or expandable structures
    private void growBuilding(int numberOfAnts, float deltaTime) {
        if (type != BuildingType.Tunnel) return;

        float effectiveGrowthRate = (width > 10)
                ? (float) (growthRate / Math.log10(width))  // Logarithmic slowdown beyond width 10
                : growthRate;

        width += effectiveGrowthRate * numberOfAnts * deltaTime;
        System.out.println("Tunnel width expanded to: " + width);
    }

    // Upgrades the building
    public void upgradeBuilding() {
        if (isUnderConstruction) return;

        upgradeLevel++;
        spriteManager.setSprite(this, upgradeLevel + 1);// Assuming sprite for each upgrade level follows construction sprites
        System.out.println(type + " upgraded to level " + upgradeLevel + "!");
    }

    // Assign a worker to the building
    public boolean assignWorker() {
        if (currentCapacity >= maxCapacity) return false;

        workersAssigned++;
        currentCapacity++;
        return true;
    }

    // Remove a worker from the building
    public boolean removeWorker() {
        if (workersAssigned <= 0) return false;

        workersAssigned--;
        currentCapacity--;
        return true;
    }
}
