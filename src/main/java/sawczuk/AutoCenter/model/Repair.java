package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import sawczuk.AutoCenter.util.DoubleUtils;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "repair")
public class Repair {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "repair_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "repair_id_gen", sequenceName = "repair_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "mileage")
    private Integer mileage;
    @Column(name = "description")
    private String description;
    @Column(name = "cost")
    private Double cost;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exploitation_type")
    private ExploitationType exploitationType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_car_id")
    private Car car;

    public Repair() {
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExploitationType getExploitationType() {
        return exploitationType;
    }

    public void setExploitationType(ExploitationType exploitationType) {
        this.exploitationType = exploitationType;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = DoubleUtils.truncateDouble(cost);
    }
}
