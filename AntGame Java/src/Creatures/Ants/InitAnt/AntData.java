package Creatures.Ants.InitAnt;

import java.util.HashMap;
import java.util.Map;

public class AntData {

    // Centralized initialization for all species
    public static Map<String, Map<String, AntEntity>> initializeAntData() {
        Map<String, Map<String, AntEntity>> speciesData = new HashMap<>();

        // Example: Adding species "FormicaRufa" with its roles
        speciesData.put("FormicaRufa", Map.of(
                "Forager", createAntEntity(
                        Map.of("ScoutArea", 1.2f, "Harvest", 1.0f),
                        Map.of("Venomous", 0.0f, "Camouflage", 0.0f),
                        Map.of("Navigation", 8, "Foraging", 9),
                        Map.of("Health", 120f, "Speed", 1.3f),
                        Map.of("Offensive", Map.of("Flanking", 0, "Charge", 0))
                ),
                "Soldier", createAntEntity(
                        Map.of("Patrol", 1.3f, "Pillage", 1.4f),
                        Map.of("Shield", 0.5f, "Aggressiveness", 1.2f),
                        Map.of("Guarding", 6, "Defense", 7),
                        Map.of("Health", 150f, "Strength", 1.5f),
                        Map.of()
                )
        ));

        return speciesData;
    }

    // Helper method to create an AntEntity
    private static AntEntity createAntEntity(
            Map<String, Float> efficiencies,
            Map<String, Float> traits,
            Map<String, Integer> skills,
            Map<String, Float> stats,
            Map<String, Map<String, Integer>> knowledge
    ) {
        return new AntEntity(efficiencies, traits, skills, stats, knowledge);
    }
}