package sawczuk.AutoCenter.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fuel_economy")
public class FuelEconomy {
    @Column(name = "id")
    @GenericGenerator(
            name = "fuelEconomySequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fuel_economy_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "fuelEconomySequenceGenerator")
    private Long id;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="user_car_id")
    private UserCar userCar;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="driving_type")
    private DrivingType drivingType;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="fuel_type")
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
