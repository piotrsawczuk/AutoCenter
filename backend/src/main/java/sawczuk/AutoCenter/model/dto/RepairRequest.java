package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RepairRequest {
    private LocalDate date;
    private Integer mileage;
    private String description;
    private Double cost;
    private Integer exploitationType;
}