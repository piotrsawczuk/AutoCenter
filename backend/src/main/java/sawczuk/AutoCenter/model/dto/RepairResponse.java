package sawczuk.AutoCenter.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RepairResponse {
    private Long id;
    private LocalDate date;
    private Integer mileage;
    private String description;
    private Double cost;
    private ExploitationTypeResponse exploitationType;
}
