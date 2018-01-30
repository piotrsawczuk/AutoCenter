package sawczuk.AutoCenter.model;

import java.util.HashMap;
import java.util.Map;

public enum FuelType {
    Petrol(1),
    Diesel(2),
    LPG(3);

    private final int value;
    private static Map map = new HashMap<>();

    FuelType(int value) {
        this.value = value;
    }

    static {
        for (FuelType fuelType : FuelType.values()) {
            map.put(fuelType.value, fuelType);
        }
    }

    public static FuelType valueOf(int fuelType) {
        return (FuelType) map.get(fuelType);
    }

    public int getValue() {
        return value;
    }
}