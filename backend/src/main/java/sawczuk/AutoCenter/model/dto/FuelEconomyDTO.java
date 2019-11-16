package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuelEconomyDTO {
    private LocalDate date;
    private Integer drivingType;
    private Integer fuelType;
    private Double distanceDriven;
    private Double fuelAmountFilled;
    private Double pricePerLitre;
}
