package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "fuel_economy")
public class FuelEconomy {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "fuel_economy_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "fuel_economy_id_gen", sequenceName = "fuel_economy_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private UserCar userCar;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_type")
    private DrivingType drivingType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type")
    private FuelType fuelType;
    @Column(name = "distance")
    private Double distance;
    @Column(name = "fuel_used")
    private Double fuelUsed;
    @Column(name = "consumption")
    private Double consumption;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCar getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCar userCar) {
        this.userCar = userCar;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public DrivingType getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(DrivingType drivingType) {
        this.drivingType = drivingType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getFuelUsed() {
        return fuelUsed;
    }

    public void setFuelUsed(Double fuelUsed) {
        this.fuelUsed = fuelUsed;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }
}
