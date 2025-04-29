package Creatures.Ants.AntLabor;

import java.util.HashMap;
import java.util.Map;

public class DataVault {

    private Map<String, Map<String, Object>> antVault = new HashMap<>();

    public void createAntEntry(String antID, Map<String, Object> antData) {
        antVault.put(antID, new HashMap<>(antData));
    }

    public Map<String, Object> getAntData(String antID) {
        return antVault.get(antID);
    }

    public void updateAntData(String antID, String key, Object value) {
        Map<String, Object> antData = antVault.get(antID);
        if (antData != null) {
            antData.put(key, value);
        }
    }
}
