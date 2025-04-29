package Creatures.Ants.Management;

import java.util.HashMap;
import java.util.Map;

public class AntManager {
    // Stores data for each ant using a unique antID as the key
    private final Map<String, AntData> antDatabase = new HashMap<>();

    // Method to add a new ant entry
    public void addAnt(String antID, String species, String role, float efficiency) {
        antDatabase.put(antID, new AntData(antID, species, role, efficiency));
    }

    // Method to retrieve an individual ant's data
    public AntData getAnt(String antID) {
        return antDatabase.get(antID);
    }

    // Method to update specific information about an ant
    public void updateAnt(String antID, String field, Object newValue) {
        AntData ant = antDatabase.get(antID);
        if (ant != null) {
            switch (field.toLowerCase()) {
                case "species":
                    if (newValue instanceof String) ant.setSpecies((String) newValue);
                    break;

                case "role":
                    if (newValue instanceof String) ant.setRole((String) newValue);
                    break;

                case "efficiency":
                    if (newValue instanceof Float) ant.setEfficiency((Float) newValue);
                    break;

                default:
                    System.out.println("Unknown field: " + field);
                    break;
            }
        }
    }

    // Convenience method to display all ants' data
    public void displayAllAnts() {
        antDatabase.forEach((id, ant) -> System.out.println(ant));
    }

    // Inner class to represent individual ant data
    private static class AntData {
        private final String antID; // A unique identifier for an ant
        private String species;
        private String role;
        private float efficiency;

        // Constructor to initialize ant data
        public AntData(String antID, String species, String role, float efficiency) {
            this.antID = antID;
            this.species = species;
            this.role = role;
            this.efficiency = efficiency;
        }

        // Getters and setters
        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public float getEfficiency() {
            return efficiency;
        }

        public void setEfficiency(float efficiency) {
            this.efficiency = efficiency;
        }

        // Returns a string representation of the ant's data
        @Override
        public String toString() {
            return "AntID: " + antID +
                    ", Species: " + species +
                    ", Role: " + role +
                    ", Efficiency: " + efficiency;
        }
    }
}