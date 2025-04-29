package Creatures.Ants.InitAnt;

import java.util.HashMap;
import java.util.Map;
import Creatures.Ants.AntLabor.Ant;
        

public class EfficiencyTree {
    private final Map<String, Ant> speciesMap = new HashMap<>(); // Inline initialization

    public void addSpecies(Ant species) {
        speciesMap.put(Ant.getName(), species);
    }

    public Ant getSpecies(String speciesName) {
        return speciesMap.get(speciesName);
    }

    // Refactored getEfficiency with extracted methods and better readability
    public Float getEfficiency(String speciesName, String role, String task) {
        AntInfo roleInformation = getRoleInformation(speciesName, role);
        return (roleInformation != null) ? roleInformation.getEfficiencyForTask(task) : null;
    }

    // Extracted helper method for better readability
    private AntInfo getRoleInformation(String speciesName, String role) {
        Ant targetSpecies = getSpecies(speciesName);
        return (targetSpecies != null) ? targetSpecies.getRoleInfo(role) : null;
    }
}