package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sawczuk.AutoCenter.util.NumberUtils;

@Getter
@Setter
@NoArgsConstructor
public class FuelEconomyAverage {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double economyAverage;

    public FuelEconomyAverage(Integer drivingTypeValue, Integer fuelTypeValue, Double economyAverage) {
        this.drivingTypeValue = drivingTypeValue;
        this.fuelTypeValue = fuelTypeValue;
        this.economyAverage = NumberUtils.truncateDouble(economyAverage);
    }

    public Double getEconomyAverage() {
        return NumberUtils.truncateDouble(economyAverage);
    }

    public void setEconomyAverage(Double economyAverage) {
        this.economyAverage = NumberUtils.truncateDouble(economyAverage);
    }
}
