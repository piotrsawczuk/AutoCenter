package sawczuk.AutoCenter.model;

import lombok.Getter;
import lombok.Setter;
import sawczuk.AutoCenter.util.NumberUtils;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "fuel_economy")
@Getter
@Setter
public class FuelEconomy {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "fuel_economy_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fuel_economy_id_gen", sequenceName = "fuel_economy_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "distance_driven", columnDefinition = "NUMERIC(7,2)")
    private Double distanceDriven;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "fuel_amount_filled", columnDefinition = "NUMERIC(5,2)")
    private Double fuelAmountFilled;
    @DecimalMin(value = "0.0", message = "The value must be positive")
    @Column(name = "price_per_litre", columnDefinition = "NUMERIC(4,2)")
    private Double pricePerLitre;
    @Column(name = "consumption", columnDefinition = "NUMERIC(7,2)")
    private Double consumption;
    @Column(name = "fill_up_cost", columnDefinition = "NUMERIC(7,2)")
    private Double fillUpCost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_type")
    private DrivingType drivingType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type")
    private FuelType fuelType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;

    public FuelEconomy() {
        this.date = LocalDate.now();
    }

    public void setDistanceDriven(Double distanceDriven) {
        this.distanceDriven = NumberUtils.truncateDouble(distanceDriven);
    }

    public void setFuelAmountFilled(Double fuelAmountFilled) {
        this.fuelAmountFilled = NumberUtils.truncateDouble(fuelAmountFilled);
    }

    public void setPricePerLitre(Double pricePerLitre) {
        this.pricePerLitre = NumberUtils.truncateDouble(pricePerLitre);
    }

    public void setConsumption(Double consumption) {
        this.consumption = NumberUtils.truncateDouble(consumption);
    }

    public void setFillUpCost(Double fillUpCost) {
        this.fillUpCost = NumberUtils.truncateDouble(fillUpCost);
    }

}
