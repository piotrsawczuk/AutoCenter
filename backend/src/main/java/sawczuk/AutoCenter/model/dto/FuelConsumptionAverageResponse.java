package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuelConsumptionAverageResponse {
    Integer drivingTypeValue;
    Integer fuelTypeValue;
    Double consumptionAverage;
}
