package Creatures.Ants.InitAnt;

import java.util.Map;

public class AntEntity {
    private Map<String, Float> efficiencies; // Efficiency values for tasks
    private Map<String, Float> traits;       // Traits with values
    private Map<String, Integer> skills;    // Skill levels for abilities
    private Map<String, Float> stats;       // Stats like health, speed, etc.
    private Map<String, Map<String, Integer>> knowledge; // Knowledge categories

    public AntEntity(Map<String, Float> efficiencies,
                     Map<String, Float> traits,
                     Map<String, Integer> skills,
                     Map<String, Float> stats,
                     Map<String, Map<String, Integer>> knowledge) {
        this.efficiencies = efficiencies;
        this.traits = traits;
        this.skills = skills;
        this.stats = stats;
        this.knowledge = knowledge;
    }

    public Map<String, Float> getEfficiencies() {
        return efficiencies;
    }

    public Map<String, Float> getTraits() {
        return traits;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public Map<String, Float> getStats() {
        return stats;
    }

    public Map<String, Map<String, Integer>> getKnowledge() {
        return knowledge;
    }
}