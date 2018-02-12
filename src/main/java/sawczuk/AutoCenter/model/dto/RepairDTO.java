package sawczuk.AutoCenter.model.dto;

import java.time.LocalDate;

public class RepairDTO {
    private LocalDate date;
    private Integer mileage;
    private String description;
    private Double cost;

    public LocalDate getDate() {
        return date;
    }

    public Integer getMileage() {
        return mileage;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost() {
        return cost;
    }
}