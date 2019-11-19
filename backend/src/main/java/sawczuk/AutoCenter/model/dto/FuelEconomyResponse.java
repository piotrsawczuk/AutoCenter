package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuelEconomyResponse {
    private Long id;
    private LocalDate date;
    private Double distanceDriven;
    private Double fuelAmountFilled;
    private Double pricePerLitre;
    private Double consumption;
    private Double fillUpCost;
    private DrivingTypeResponse drivingType;
    private FuelTypeResponse fuelType;
}
