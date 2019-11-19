package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelEconomyAverageResponse {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double economyAverage;
}
