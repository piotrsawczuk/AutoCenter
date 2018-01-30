package sawczuk.AutoCenter.model;

import java.util.HashMap;
import java.util.Map;

public enum FuelEconomyType {
    City(1),
    Highway(2),
    Mixed(3);

    private final int value;
    private static Map map = new HashMap<>();

    FuelEconomyType(int value) {
        this.value = value;
    }

    static {
        for (FuelEconomyType fuelEconomyType : FuelEconomyType.values()) {
            map.put(fuelEconomyType.value, fuelEconomyType);
        }
    }

    public static FuelEconomyType valueOf(int fuelEconomyType) {
        return (FuelEconomyType) map.get(fuelEconomyType);
    }

    public int getValue() {
        return value;
    }
}