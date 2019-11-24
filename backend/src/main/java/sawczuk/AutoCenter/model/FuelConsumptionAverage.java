package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sawczuk.AutoCenter.util.NumberUtils;

@Getter
@Setter
@NoArgsConstructor
public class FuelConsumptionAverage {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double consumptionAverage;

    public FuelConsumptionAverage(Integer drivingTypeValue, Integer fuelTypeValue, Double consumptionAverage) {
        this.drivingTypeValue = drivingTypeValue;
        this.fuelTypeValue = fuelTypeValue;
        this.consumptionAverage = NumberUtils.truncateDouble(consumptionAverage);
    }

    public Double getConsumptionAverage() {
        return NumberUtils.truncateDouble(consumptionAverage);
    }

    public void setConsumptionAverage(Double consumptionAverage) {
        this.consumptionAverage = NumberUtils.truncateDouble(consumptionAverage);
    }
}
