package Creatures.Ants.AntLabor;

import java.util.List;
import Creatures.Ants.AntLabor.Task;

// Abstract class PrioritizationMethod with shared logic
public abstract class PrioritizationMethod {
    // Default prioritization: The nearest ant is assigned the task
    public static final PrioritizationMethod DEFAULT = new PrioritizationMethod() {
        @Override
        public Ant assignTask(Task task, List<Ant> availableAnts) {
            if (availableAnts.isEmpty()) {
                return null; // No ants available
            }
            // Find and return the closest ant using stream min
            return availableAnts.stream()
                    .min((ant1, ant2) -> Double.compare(
                            calculateEuclideanDistance(task, ant1),
                            calculateEuclideanDistance(task, ant2)))
                    .orElse(null);
        }
    };

    // Skill-based prioritization or other prioritization methods can be defined similarly here.

    /**
     * Calculates the Euclidean distance between a task and an ant.
     * This method is shared and can be reused across prioritization strategies.
     *
     * @param task Task location
     * @param ant  Ant location
     * @return Euclidean distance
     */
    protected static double calculateEuclideanDistance(Task task, Ant ant) {
        double dx = task.getX() - ant.getX();
        double dy = task.getY() - ant.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    // Skill-Based prioritization: Expandable skeleton for complex calculations
    public static final PrioritizationMethod SKILL_BASED = new PrioritizationMethod() {
        @Override
        public Ant assignTask(Task task, List<Ant> availableAnts) {
            // Skeleton for a complex calculation
            Ant bestAnt = null;
            double highestScore = Double.MIN_VALUE;

            for (Ant ant : availableAnts) {
                // Example for scoring: Can include skills, efficiency, energy, etc.
                double score = calculateSkillScore(task, ant);
                if (score > highestScore) {
                    highestScore = score;
                    bestAnt = ant;
                }
            }
            return bestAnt; // Return the ant with the highest score
        }

        private double calculateSkillScore(Task task, Ant ant) {
            // Integrate complex calculations here based on individual ant/unit data
            // Example: Combine multiple factors like skill, efficiency, energy, etc.
            double skillMatching = task.getRequiredSkill().equals(ant.getSkill()) ? 1.0 : 0.5;
            double efficiency = ant.getEfficiency();
            double energyFactor = ant.getEnergy() / 100.0; // Normalized energy

            return skillMatching * efficiency * energyFactor;
        }
    };

    public abstract Ant assignTask(Task task, List<Ant> availableAnts);
}